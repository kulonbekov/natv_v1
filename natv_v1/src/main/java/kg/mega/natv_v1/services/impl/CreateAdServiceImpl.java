package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.dtos.OrderDatesDto;
import kg.mega.natv_v1.models.dtos.OrderDto;
import kg.mega.natv_v1.models.dtos.TextDto;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.entities.OrderDates;
import kg.mega.natv_v1.models.enums.OrderStatus;
import kg.mega.natv_v1.models.requests.OrderRequest;
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
    private final TextService textService;
    private final OrderService orderService;
    private final DiscountRep discountRep;
    private final PriceRep priceRep;
    private final ChannelOrderService channelOrderService;
    private final ChannelService channelService;
    private final OrderDatesService orderDatesService;

    @Override
    public OrderResponse newCreateAd(OrderRequest orderRequest) {
        OrderResponse orderResponse = new OrderResponse();
        List<ChannelResponse> channelResponses = new ArrayList<>();


        TextDto textDto = new TextDto();
        textDto.setText(orderRequest.getText());
        textDto = textService.save(textDto);


        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDate(new Date());
        orderDto.setClientEmail(orderRequest.getClientEmail());
        orderDto.setClientFIO(orderRequest.getClientFIO());
        orderDto.setClientPhone(orderRequest.getClientPhone());
        orderDto.setTextDto(textDto);
        orderDto.setOrderStatus(OrderStatus.CREATED);




        double totalPrice = 0.0;


        try {
            for (int i = 0; i < orderRequest.getChannelRequest().size(); i++) {
                ChannelResponse channelResponse = new ChannelResponse();
                int daysCount = orderRequest.getChannelRequest().get(i).getDateList().size();
                Long channelId = orderRequest.getChannelRequest().get(i).getChannelId();
                List<Discount> discounts = discountRep.getDiscounts(channelId);
                int discount = getDiscount(discounts, daysCount);
                double price = priceRep.getPrice(channelId).getPricePerSymbol() * textDto.getSymbolCount() * daysCount;
                double priceWithDiscount = price - ((price * discount) / 100);
                channelResponse.setChannelId(channelId);
                channelResponse.setPrice(price);
                channelResponse.setPriceWithDiscount(priceWithDiscount);
                channelResponse.setDateList(orderRequest.getChannelRequest().get(i).getDateList());
                channelResponses.add(channelResponse);
                totalPrice += priceWithDiscount;

            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

        orderDto.setOrderSum(totalPrice);
        orderDto = orderService.save(orderDto);

        for (int i = 0; i < orderRequest.getChannelRequest().size(); i++) {
            int daysCount = orderRequest.getChannelRequest().get(i).getDateList().size();
            ChannelOrderDto channelOrderDto = new ChannelOrderDto();
            channelOrderDto.setDaysCount(daysCount);
            channelOrderDto.setChannelDto(channelService.findById(orderRequest.getChannelRequest().get(i).getChannelId()));
            channelOrderDto.setOrderDto(orderDto);
            channelOrderService.save(channelOrderDto);
            for (int j = 0; j < orderRequest.getChannelRequest().get(i).getDateList().size(); j++) {
                OrderDatesDto orderDatesDto = new OrderDatesDto();
                orderDatesDto.setDate(orderRequest.getChannelRequest().get(i).getDateList().get(j));
                orderDatesDto.setChannelOrderDto(channelOrderDto);
                orderDatesService.save(orderDatesDto);
            }

        }

        orderResponse.setClientFIO(orderDto.getClientFIO());
        orderResponse.setClientEmail(orderDto.getClientEmail());
        orderResponse.setClientPhone(orderDto.getClientPhone());
        orderResponse.setText(textDto.getText());
        orderResponse.setOrderStatus(orderDto.getOrderStatus());
        orderResponse.setTotalPrice(orderDto.getOrderSum());
        orderResponse.setChannelResponseList(channelResponses);

        return orderResponse;
    }

    private int getDiscount(List<Discount> discounts, int daysCount) {
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
}
