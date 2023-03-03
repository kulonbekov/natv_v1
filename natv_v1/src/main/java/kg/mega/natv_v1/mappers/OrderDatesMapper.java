package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.OrderDatesDto;
import kg.mega.natv_v1.models.entities.OrderDates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderDatesMapper {

    OrderDatesMapper INSTANCE = Mappers.getMapper(OrderDatesMapper.class);

    @Mapping(source = "channelOrderDto", target = "channelOrder")
    @Mapping(source = "channelOrderDto.channelDto", target = "channelOrder.channel")
    @Mapping(source = "channelOrderDto.orderDto", target = "channelOrder.order")
    @Mapping(source = "channelOrderDto.orderDto.textDto", target = "channelOrder.order.text")
    OrderDates orderDatesDtoToOrderDates(OrderDatesDto orderDatesDto);

    List<OrderDates> orderDatesDtoToOrderDatesList(List<OrderDatesDto> orderDatesDto);

    @Mapping(source = "channelOrder", target = "channelOrderDto")
    @Mapping(source = "channelOrder.channel", target = "channelOrderDto.channelDto")
    @Mapping(source = "channelOrder.order", target = "channelOrderDto.orderDto")
    @Mapping(source = "channelOrder.order.text", target = "channelOrderDto.orderDto.textDto")
    OrderDatesDto orderDatesToOrderDatesDto(OrderDates orderDates);

    List<OrderDatesDto> orderDatesToOrderDatesDtoList(List<OrderDates> orderDates);


}
