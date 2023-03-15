package kg.mega.natv_v1.mappers.mainMapper.impl;

import kg.mega.natv_v1.dao.ChannelOrderRep;
import kg.mega.natv_v1.dao.ChannelRep;
import kg.mega.natv_v1.mappers.mainMapper.OrderSaveMapper;
import kg.mega.natv_v1.models.dtos.*;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import kg.mega.natv_v1.models.enums.OrderStatus;
import kg.mega.natv_v1.models.requests.ChannelRequest;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.ChannelResponse;
import kg.mega.natv_v1.models.responses.ChannelSaveResponse;
import kg.mega.natv_v1.models.responses.OrderResponse;
import kg.mega.natv_v1.models.responses.PriceResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderSaveMapperImpl implements OrderSaveMapper {
    private final ChannelRep channelRep;
    private final ChannelOrderRep channelOrderRep;

    public OrderSaveMapperImpl(ChannelRep channelRep,
                               ChannelOrderRep channelOrderRep) {
        this.channelRep = channelRep;
        this.channelOrderRep = channelOrderRep;
    }

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
    public DayDto getOrderDatesDto(OrderRequest orderRequest, ChannelOrderDto channelOrderDto, Date j) {
        DayDto dayDto = new DayDto();
        dayDto.setDate(j);
        dayDto.setChannelOrderDto(channelOrderDto);

        return dayDto;
    }

    @Override
    public ChannelOrderDto getChannelOrderDto(ChannelDto channelDto, OrderDto orderDto, OrderRequest orderRequest, ChannelRequest channelRequest, PriceResponse priceResponse) {
        int daysCount = channelRequest.getDateList().size();
        ChannelOrderDto channelOrderDto = new ChannelOrderDto();
        channelOrderDto.setDaysCount(daysCount);
        channelOrderDto.setChannelDto(channelDto);
        channelOrderDto.setOrderDto(orderDto);
        channelOrderDto.setPrice(priceResponse.getPrice());
        channelOrderDto.setPriceWithDiscount(priceResponse.getPriceWithDiscount());
        return channelOrderDto;
    }

    @Override
    public Channel channelSaveResponseToChannel(ChannelSaveResponse channelDto) {

        Channel channel = new Channel();
        channel.setId(channelDto.getId());
        channel.setChannelName(channelDto.getChannelName());
        channel.setChannelStatus(ChannelStatus.TRUE);
        channel.setCreatedDate(new Date());
        channel.setLogoPath(channelDto.getLogoPath());

        return channel;
    }


}
