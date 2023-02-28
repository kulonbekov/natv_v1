package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.OrderDatesDto;
import kg.mega.natv_v1.models.entities.OrderDates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OrderDatesMapper {

    @Mapping(source = "orderDto", target = "order")
    @Mapping(source = "orderDto.channelDto", target = "order.channel")
    @Mapping(source = "orderDto.textDto", target = "order.text")
    @Mapping(source = "orderDto.bannerDto", target = "order.banner")
    OrderDates orderDatesDtoToOrderDates (OrderDatesDto orderDatesDto);
    List<OrderDates> orderDatesDtoToOrderDatesList (List<OrderDatesDto> orderDatesDto);
    @Mapping(source = "order", target = "orderDto")
    @Mapping(source = "order.channel", target = "orderDto.channelDto")
    @Mapping(source = "order.text", target = "orderDto.textDto")
    @Mapping(source = "order.banner", target = "orderDto.bannerDto")
    OrderDatesDto orderDatesToOrderDatesDto (OrderDates orderDates);
    List<OrderDatesDto> orderDatesToOrderDatesDtoList (List<OrderDates> orderDates);


}
