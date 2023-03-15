package kg.mega.natv_v1.services.mainOperations.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.mappers.mainMapper.EmailMapper;
import kg.mega.natv_v1.mappers.mainMapper.OrderSaveMapper;
import kg.mega.natv_v1.models.dtos.*;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.requests.ChannelRequest;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.responses.ChannelResponse;
import kg.mega.natv_v1.models.responses.OrderResponse;
import kg.mega.natv_v1.models.responses.PriceResponse;
import kg.mega.natv_v1.services.crudOperations.*;
import kg.mega.natv_v1.services.email.EmailService;
import kg.mega.natv_v1.services.mainOperations.GetCostAdsService;
import kg.mega.natv_v1.services.mainOperations.OrderAdvertisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderAdvertisingServiceImpl implements OrderAdvertisingService {
    private final OrderService orderService;
    private final DiscountRep discountRep;
    private final ChannelOrderService channelOrderService;
    private final ChannelService channelService;
    private final DayService dayService;
    private final OrderSaveMapper orderSaveMapper;
    private final TextService textService;
    private final EmailMapper emailMapper;
    private final EmailService emailService;
    private final GetCostAdsService getCostAdsService;

    @Override
    public OrderResponse CreateAdvertising(OrderRequest orderRequest) {

        OrderDto orderDto;
        List<ChannelResponse> channelResponses = new ArrayList<>();

        TextDto textDto = getTextDto(orderRequest); //Создание нового TextDto и передать Текст рекламы

        try {
            channelResponses = saveChannelResponse(orderRequest, textDto); // сформировать json для ChannelResponse
            orderDto = orderService.save(orderSaveMapper.orderRequestToOrder(orderRequest, textDto, getTotalPrice(orderRequest, textDto))); // Создание и сохрание нового записа в таблицу "tb_order"
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Save error 'Order' ");
        }
        try {
            saveChannelOrder(orderRequest, orderDto, textDto); //Сохранение нового записа в промежуточную таблицу "tb_channel_order" и сохранение записей дат в таблицу "tb_order_dates"
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Save error 'ChannelOrder'");
        }

        OrderResponse orderResponse = orderSaveMapper.getOrderResponse(orderDto, textDto, channelResponses);

        settingEmail(orderResponse); //Отправка письма к клиенту

        return orderResponse; //возвращает json для "orderResponse"
    }


    private List<ChannelResponse> saveChannelResponse(OrderRequest orderRequest, TextDto textDto) { // сформировать json для ChannelResponse

        List<ChannelResponse> channelResponses = orderRequest.getChannelRequest().stream()
                .map(item -> getChannelResponse(item, textDto))
                .collect(Collectors.toList());
        return channelResponses;
    }

    private ChannelResponse getChannelResponse(ChannelRequest item, TextDto textDto) { // Получить объект ChannelResponse

        ChannelResponse channelResponse = new ChannelResponse();

        channelResponse.setChannelId(item.getChannelId());
        channelResponse.setPrice(getPriceResponse(item, textDto).getPrice());
        channelResponse.setPriceWithDiscount(getPriceResponse(item, textDto).getPriceWithDiscount());
        channelResponse.setDateList(item.getDateList());

        return channelResponse;
    }

    private void saveChannelOrder(OrderRequest orderRequest, OrderDto orderDto, TextDto textDto) { //Сохранение нового записа в промежуточную таблицу "tb_channel_order" и сохранение записей дат в таблицу "tb_order_dates"

        for (ChannelRequest i : orderRequest.getChannelRequest()) {
            ChannelDto channelDto = channelService.findById(i.getChannelId());
            ChannelOrderDto channelOrderDto = channelOrderService.save(orderSaveMapper.getChannelOrderDto(channelDto, orderDto, orderRequest, i, getPriceResponse(i,textDto)));
            for (Date j : i.getDateList()) {
                DayDto dayDto = orderSaveMapper.getOrderDatesDto(orderRequest, channelOrderDto, j);
                dayService.save(dayDto);
            }
        }
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

    private TextDto getTextDto(OrderRequest orderRequest) { //Сохранение нового записа в таблицу tb_text "Текст обьявления"
        TextDto textDto = new TextDto();
        textDto.setText(orderRequest.getText());
        return textService.save(textDto);
    }

    private double getTotalPrice(OrderRequest orderRequest, TextDto textDto) { //Получить total price по всем каналам
        double totalPrice = 0.0;

        for (ChannelRequest item : orderRequest.getChannelRequest()) {
            totalPrice += getPriceResponse(item, textDto).getPriceWithDiscount();
        }
        return totalPrice;
    }

    private PriceResponse getPriceResponse(ChannelRequest item, TextDto textDto) { // Метод для подсчета price и priceWithDiscount
        PriceResponse priceResponse = new PriceResponse();

        int daysCount = item.getDateList().size();
        Long channelId = item.getChannelId();
        List<Discount> discounts = discountRep.getDiscounts(channelId);
        int discount = getDiscount(discounts, daysCount);
        double price = getCostAdsService.getPrice(channelId) * textDto.getSymbolCount() * daysCount;
        double priceWithDiscount = price - ((price * discount) / 100);

        priceResponse.setPrice(price);
        priceResponse.setPriceWithDiscount(priceWithDiscount);

        return priceResponse;
    }

    private void settingEmail(OrderResponse orderResponse) {
        String email = orderResponse.getClientEmail();
        String subject = "Advertising " + new Date();
        String text = emailMapper.orderResponseToString(orderResponse);

        try {
            emailService.send(email, subject, text);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
