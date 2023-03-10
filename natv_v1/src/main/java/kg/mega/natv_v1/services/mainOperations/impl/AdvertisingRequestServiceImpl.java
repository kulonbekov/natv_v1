package kg.mega.natv_v1.services.mainOperations.impl;

import kg.mega.natv_v1.dao.ChannelOrderRep;
import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.mainMapper.EmailMapper;
import kg.mega.natv_v1.mappers.mainMapper.OrderSaveMapper;
import kg.mega.natv_v1.models.dtos.*;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.entities.Price;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.responses.ChannelResponse;
import kg.mega.natv_v1.models.responses.OrderResponse;
import kg.mega.natv_v1.services.crudOperations.*;
import kg.mega.natv_v1.services.email.EmailService;
import kg.mega.natv_v1.services.mainOperations.AdvertisingRequestService;
import kg.mega.natv_v1.services.mainOperations.GetCostAdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisingRequestServiceImpl implements AdvertisingRequestService {
    private final OrderService orderService;
    private final DiscountRep discountRep;
    private final ChannelOrderService channelOrderService;
    private final ChannelService channelService;
    private final OrderDatesService orderDatesService;
    private final OrderSaveMapper orderSaveMapper;
    private final TextService textService;
    private final EmailMapper emailMapper;
    private final EmailService emailService;
    private final GetCostAdsService getCostAdsService;

    @Override
    public OrderResponse newCreateAd(OrderRequest orderRequest) {
        OrderDto orderDto = new OrderDto();
        double totalPrice = 0.0;
        List<ChannelResponse> channelResponses = new ArrayList<>();
        TextDto textDto = getTextDto(orderRequest); //Создание нового TextDto и передать Текст рекламы

        try{
            channelResponses = saveChannelResponse(orderRequest, textDto); // сформировать json для ChannelResponse
            totalPrice = getTotalPrice(orderRequest, textDto);
            orderDto = orderService.save(orderSaveMapper.orderRequestToOrder(orderRequest, textDto, totalPrice)); // Создание и сохрание нового записа в таблицу "tb_order"
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Save error 'Order' ");
        }
        try{
            saveChannelOrder(orderRequest, orderDto); //Сохранение нового записа в промежуточную таблицу "tb_channel_order" и сохранение записей дат в таблицу "tb_order_dates"
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("\n" + "Save error 'ChannelOrder'");
        }

        OrderResponse orderResponse = orderSaveMapper.getOrderResponse(orderDto, textDto, channelResponses);
        settingEmail(orderResponse); //Отправка письма к клиенту
        return  orderResponse; //возвращает json для "orderResponse"
    }



    @Override
    public void settingEmail(OrderResponse orderResponse) {
        String email = orderResponse.getClientEmail();
        String subject = "Advertising" + new Date();
        String text = emailMapper.orderResponseToString(orderResponse);

        try{
            emailService.send(email,subject,text);
        }catch (Exception e){
            e.getMessage();
        }
    }

    private TextDto getTextDto(OrderRequest orderRequest) { //Сохранение нового записа в таблицу tb_text "Текст обьявления"
        TextDto textDto = new TextDto();
        textDto.setText(orderRequest.getText());
        return textService.save(textDto);
    }


    private int getDiscount(List<Discount> discounts, int daysCount) { //Получить из базы текущие скидки по каналу и получить актуальную скидку по заданному количеству дней
        int discount = 0;

        for (Discount item : discounts) {
            if (item.getStartDate().before(new Date()) &&
                    item.getEndDate().after(new Date()) &&
                    item.getDiscountDays() <= daysCount) {
                discount = item.getDiscount();
            }
        }
        return discount;
    }

    private void saveChannelOrder(OrderRequest orderRequest, OrderDto orderDto) { //Сохранение нового записа в промежуточную таблицу "tb_channel_order" и сохранение записей дат в таблицу "tb_order_dates"

        for (int i = 0; i < orderRequest.getChannelRequest().size(); i++) {
            ChannelDto channelDto = channelService.findById(orderRequest.getChannelRequest().get(i).getChannelId());
            ChannelOrderDto channelOrderDto = channelOrderService.save(orderSaveMapper.getChannelOrderDto(channelDto, orderDto, orderRequest, i));
            for (int j = 0; j < orderRequest.getChannelRequest().get(i).getDateList().size(); j++) {
                OrderDatesDto orderDatesDto = orderSaveMapper.getOrderDatesDto(orderRequest, channelOrderDto, i, j);
                orderDatesService.save(orderDatesDto);
            }
        }
    }

    private List<ChannelResponse> saveChannelResponse(OrderRequest orderRequest, TextDto textDto) { // сформировать json для ChannelResponse

        List<ChannelResponse> channelResponses = new ArrayList<>();
        for (int i = 0; i < orderRequest.getChannelRequest().size(); i++) {
            ChannelResponse channelResponse = new ChannelResponse();
            int daysCount = orderRequest.getChannelRequest().get(i).getDateList().size();
            Long channelId = orderRequest.getChannelRequest().get(i).getChannelId();
            List<Discount> discounts = discountRep.getDiscounts(channelId);
            int discount = getDiscount(discounts, daysCount);
            double price = getCostAdsService.getPrice(channelId) * textDto.getSymbolCount() * daysCount;
            double priceWithDiscount = price - ((price * discount) / 100);

            channelResponse.setChannelId(channelId);
            channelResponse.setPrice(price);
            channelResponse.setPriceWithDiscount(priceWithDiscount);
            channelResponse.setDateList(orderRequest.getChannelRequest().get(i).getDateList());
            channelResponses.add(channelResponse);

        }
        return channelResponses;
    }
    private  double getTotalPrice (OrderRequest orderRequest, TextDto textDto){
        double totalPrice = 0.0;
        for (int i = 0; i < orderRequest.getChannelRequest().size(); i++) {
            int daysCount = orderRequest.getChannelRequest().get(i).getDateList().size();
            Long channelId = orderRequest.getChannelRequest().get(i).getChannelId();
            List<Discount> discounts = discountRep.getDiscounts(channelId);
            int discount = getDiscount(discounts, daysCount);
            double price = getCostAdsService.getPrice(channelId) * textDto.getSymbolCount() * daysCount;
            double priceWithDiscount = price - ((price * discount) / 100);
            totalPrice += priceWithDiscount;

        }
        return totalPrice;
    }
}
