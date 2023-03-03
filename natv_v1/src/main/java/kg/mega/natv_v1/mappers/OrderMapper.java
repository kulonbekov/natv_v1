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


    @Mapping(source = "textDto", target = "text")
    Order orderDtoToOrder (OrderDto orderDto);
    List<Order> orderDtoToOrderList (List<OrderDto> orderDto);

    @Mapping(source = "text", target = "textDto")
    OrderDto orderToOrderDto (Order order);
    List<OrderDto> orderToOrderDtoList (List<Order> order);


}
