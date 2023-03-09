package kg.mega.natv_v1.mappers.mainMapper;

import kg.mega.natv_v1.models.dtos.*;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.*;

import java.util.List;

public interface OrderSaveMapper {

    OrderDto orderRequestToOrder(OrderRequest orderRequest, TextDto textDto, double totalPrice);

    PriceResponse requestToResponse(PriceRequest priceRequest, double price, double priceWithDiscount);

    OrderResponse getOrderResponse(OrderDto orderDto, TextDto textDto, List<ChannelResponse> channelResponses);

    OrderDatesDto getOrderDatesDto(OrderRequest orderRequest, ChannelOrderDto channelOrderDto, int i, int j);

    ChannelOrderDto getChannelOrderDto(ChannelDto channelDto, OrderDto orderDto, OrderRequest orderRequest, int i);

    Channel channelSaveResponseToChannel (ChannelSaveResponse channelDto);


}
