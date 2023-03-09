package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.OrderEmailMapper;
import kg.mega.natv_v1.mappers.RequestMapper;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.dtos.OrderDto;
import kg.mega.natv_v1.models.dtos.TextDto;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.entities.Price;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.ChannelResponse;
import kg.mega.natv_v1.models.responses.OrderResponse;
import kg.mega.natv_v1.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateAdServiceImpl implements CreateAdService {
    private final OrderService orderService;
    private final DiscountRep discountRep;
    private final PriceRep priceRep;
    private final ChannelOrderService channelOrderService;
    private final ChannelService channelService;
    private final OrderDatesService orderDatesService;
    private final RequestMapper requestMapper;
    private final TextService textService;
    private final OrderEmailMapper orderEmailMapper;
    private final EmailService emailService;
    double totalPrice = 0.0;
    List<ChannelResponse> channelResponses = new ArrayList<>();
    OrderDto orderDto = new OrderDto();

    @Override
    public OrderResponse newCreateAd(OrderRequest orderRequest) {

        TextDto textDto = getTextDto(orderRequest); //Сохранение нового записа в таблицу tb_text "Текст обьявления"


        try {
            channelResponses = saveChannelResponse(orderRequest, textDto); // сформировать json для ChannelResponse
            orderDto = orderService.save(requestMapper.orderRequestToOrder(orderRequest, textDto, totalPrice)); // Создание и сохрание нового записа в таблицу "tb_order"
            saveChannelOrder(orderRequest, orderDto); //Сохранение нового записа в промежуточную таблицу "tb_channel_order" и сохранение записей дат в таблицу "tb_order_dates"
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.toString());
            throw new RuntimeException("Ошибка при сохранения ");
        }
        OrderResponse orderResponse = requestMapper.getOrderResponse(orderDto, textDto, channelResponses);
        settingEmail(orderResponse);
        return  orderResponse; //возвращает json для "orderResponse"
    }

    @Override
    public void settingEmail(OrderResponse orderResponse) {
        String email = orderResponse.getClientEmail();
        String subject = "Advertising" + new Date();
        String text = orderEmailMapper.orderResponseToString(orderResponse);

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
            ChannelOrderDto channelOrderDto = channelOrderService.save(requestMapper.getChannelOrderDto(channelDto, orderDto, orderRequest, i));
            for (int j = 0; j < orderRequest.getChannelRequest().get(i).getDateList().size(); j++) {
                orderDatesService.save(requestMapper.getOrderDatesDto(orderRequest, channelOrderDto, i, j));
            }
        }
    }

    private List<ChannelResponse> saveChannelResponse(OrderRequest orderRequest, TextDto textDto) { // сформировать json для ChannelResponse
        for (int i = 0; i < orderRequest.getChannelRequest().size(); i++) {
            ChannelResponse channelResponse = new ChannelResponse();
            int daysCount = orderRequest.getChannelRequest().get(i).getDateList().size();
            Long channelId = orderRequest.getChannelRequest().get(i).getChannelId();
            List<Discount> discounts = discountRep.getDiscounts(channelId);
            int discount = getDiscount(discounts, daysCount);
            double price = getPrice(channelId) * textDto.getSymbolCount() * daysCount;
            double priceWithDiscount = price - ((price * discount) / 100);
            channelResponse.setChannelId(channelId);
            channelResponse.setPrice(price);
            channelResponse.setPriceWithDiscount(priceWithDiscount);
            channelResponse.setDateList(orderRequest.getChannelRequest().get(i).getDateList());
            channelResponses.add(channelResponse);
            totalPrice += priceWithDiscount;
        }
        return channelResponses;
    }

    private double getPrice (Long id){ //Получить актуальную цену на рекламу
        double price = 0.0;
        List<Price> prices = priceRep.getPrice(id);

        for (Price item: prices) {
            if (item.getStartDate().before(new Date()) &&
                    item.getEndDate().after(new Date())){
                price = item.getPricePerSymbol();
            }
        }
        return price;
    }
}
