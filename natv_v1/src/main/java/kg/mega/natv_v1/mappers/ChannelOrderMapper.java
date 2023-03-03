package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.entities.ChannelOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChannelOrderMapper {

    ChannelOrderMapper INSTANCE = Mappers.getMapper(ChannelOrderMapper.class);

    @Mapping(source = "channelDto", target = "channel")
    @Mapping(source = "orderDto", target = "order")
    @Mapping(source = "orderDto.textDto", target = "order.text")
    ChannelOrder DtoTo(ChannelOrderDto channelOrderDto);

    List<ChannelOrder> DtoToList(List<ChannelOrderDto> channelOrderDto);

    @Mapping(source = "channel", target = "channelDto")
    @Mapping(source = "order", target = "orderDto")
    @Mapping(source = "order.text", target = "orderDto.textDto")
    ChannelOrderDto toDto(ChannelOrder channelOrder);

    List<ChannelOrderDto> toDtoList(List<ChannelOrder> channelOrder);


}
