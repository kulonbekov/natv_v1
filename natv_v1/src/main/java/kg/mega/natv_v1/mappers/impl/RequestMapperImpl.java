package kg.mega.natv_v1.mappers.impl;

import kg.mega.natv_v1.mappers.RequestMapper;
import kg.mega.natv_v1.models.dtos.*;
import kg.mega.natv_v1.models.enums.OrderStatus;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.ChannelResponse;
import kg.mega.natv_v1.models.responses.OrderResponse;
import kg.mega.natv_v1.models.responses.PriceResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestMapperImpl implements RequestMapper {
    @Override
    public OrderDto orderRequestToOrder(OrderRequest orderRequest, TextDto textDto, double totalPrice) {

        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDate(new Date());
        orderDto.setClientEmail(orderRequest.getClientEmail());
        orderDto.setClientFIO(orderRequest.getClientFIO());
        orderDto.setClientPhone(orderRequest.getClientPhone());
        orderDto.setTextDto(textDto);
        orderDto.setOrderStatus(OrderStatus.CREATED);
        orderDto.setOrderSum(totalPrice);
        return orderDto;
    }

    @Override
    public PriceResponse requestToResponse(PriceRequest priceRequest, double price, double priceWithDiscount) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setText(priceRequest.getText());
        priceResponse.setDaysCount(priceRequest.getDaysCount());
        priceResponse.setChannelId(priceRequest.getChannelId());
        priceResponse.setPrice(price);
        priceResponse.setPriceWithDiscount(priceWithDiscount);
        return priceResponse;
    }

    @Override
    public OrderResponse getOrderResponse(OrderDto orderDto, TextDto textDto, List<ChannelResponse> channelResponses) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setClientFIO(orderDto.getClientFIO());
        orderResponse.setClientEmail(orderDto.getClientEmail());
        orderResponse.setClientPhone(orderDto.getClientPhone());
        orderResponse.setText(textDto.getText());
        orderResponse.setOrderStatus(orderDto.getOrderStatus());
        orderResponse.setTotalPrice(orderDto.getOrderSum());
        orderResponse.setChannelResponseList(channelResponses);
        return orderResponse;
    }

    @Override
    public OrderDatesDto getOrderDatesDto(OrderRequest orderRequest, ChannelOrderDto channelOrderDto, int i, int j) {
        OrderDatesDto orderDatesDto = new OrderDatesDto();
        orderDatesDto.setDate(orderRequest.getChannelRequest().get(i).getDateList().get(j));
        orderDatesDto.setChannelOrderDto(channelOrderDto);

        return orderDatesDto;
    }

    @Override
    public ChannelOrderDto getChannelOrderDto(ChannelDto channelDto, OrderDto orderDto, OrderRequest orderRequest, int i) {
        int daysCount = orderRequest.getChannelRequest().get(i).getDateList().size();
        ChannelOrderDto channelOrderDto = new ChannelOrderDto();
        channelOrderDto.setDaysCount(daysCount);
        channelOrderDto.setChannelDto(channelDto);
        channelOrderDto.setOrderDto(orderDto);
        return channelOrderDto;
    }
}
