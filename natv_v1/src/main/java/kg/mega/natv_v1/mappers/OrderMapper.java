package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.OrderDto;
import kg.mega.natv_v1.models.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "channelDto", target = "channel")
    @Mapping(source = "textDto", target = "text")
    @Mapping(source = "bannerDto", target = "banner")
    Order orderDtoToOrder (OrderDto orderDto);
    List<Order> orderDtoToOrderList (List<OrderDto> orderDto);
    @Mapping(source = "channel", target = "channelDto")
    @Mapping(source = "text", target = "textDto")
    @Mapping(source = "banner", target = "bannerDto")
    OrderDto orderToOrderDto (Order order);
    List<OrderDto> orderToOrderDto (List<Order> order);


}
