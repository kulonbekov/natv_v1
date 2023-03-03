package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.*;
import kg.mega.natv_v1.models.entities.ChannelOrder;
import kg.mega.natv_v1.models.entities.Order;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.ChannelResponse;
import kg.mega.natv_v1.models.responses.OrderResponse;
import kg.mega.natv_v1.models.responses.PriceResponse;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface RequestMapper {

    OrderDto orderRequestToOrder (OrderRequest orderRequest, TextDto textDto, double totalPrice);
    PriceResponse requestToResponse (PriceRequest priceRequest, double price, double priceWithDiscount);
    OrderResponse getOrderResponse(OrderDto orderDto, TextDto textDto, List<ChannelResponse> channelResponses);
    OrderDatesDto getOrderDatesDto(OrderRequest orderRequest, ChannelOrderDto channelOrderDto, int i, int j);
    ChannelOrderDto getChannelOrderDto(ChannelDto channelDto,OrderDto orderDto, OrderRequest orderRequest, int i);


}
