package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.OrderDatesDto;
import kg.mega.natv_v1.models.entities.OrderDates;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderDatesMapper {

    OrderDatesMapper INSTANCE = Mappers.getMapper(OrderDatesMapper.class);


    OrderDates orderDatesDtoToOrderDates (OrderDatesDto orderDatesDto);
    List<OrderDates> orderDatesDtoToOrderDatesList (List<OrderDatesDto> orderDatesDto);

    OrderDatesDto orderDatesToOrderDatesDto (OrderDates orderDates);
    List<OrderDatesDto> orderDatesToOrderDatesDtoList (List<OrderDates> orderDates);


}
