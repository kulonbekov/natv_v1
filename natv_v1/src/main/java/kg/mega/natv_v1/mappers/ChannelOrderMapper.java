package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.entities.ChannelOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChannelOrderMapper {

    ChannelOrderMapper INSTANCE = Mappers.getMapper(ChannelOrderMapper.class);

    ChannelOrder DtoTo (ChannelOrderDto channelOrderDto);
    List<ChannelOrder> DtoToList (List<ChannelOrderDto> channelOrderDto);
    ChannelOrderDto toDto (ChannelOrder channelOrder);
    List<ChannelOrderDto> toDtoList (List<ChannelOrder> channelOrder);



}
