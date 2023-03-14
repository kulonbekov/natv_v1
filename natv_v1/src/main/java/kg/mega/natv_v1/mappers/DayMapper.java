package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.DayDto;
import kg.mega.natv_v1.models.entities.Day;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DayMapper {

    DayMapper INSTANCE = Mappers.getMapper(DayMapper.class);

    @Mapping(source = "channelOrderDto", target = "channelOrder")
    @Mapping(source = "channelOrderDto.channelDto", target = "channelOrder.channel")
    @Mapping(source = "channelOrderDto.orderDto", target = "channelOrder.order")
    @Mapping(source = "channelOrderDto.orderDto.textDto", target = "channelOrder.order.text")
    Day dayDtoToDay(DayDto dayDto);

    List<Day> dayDtoToDayList(List<DayDto> dayDto);

    @Mapping(source = "channelOrder", target = "channelOrderDto")
    @Mapping(source = "channelOrder.channel", target = "channelOrderDto.channelDto")
    @Mapping(source = "channelOrder.order", target = "channelOrderDto.orderDto")
    @Mapping(source = "channelOrder.order.text", target = "channelOrderDto.orderDto.textDto")
    DayDto dayToDayDto(Day day);

    List<DayDto> dayToDayDtoList(List<Day> dates);



}
