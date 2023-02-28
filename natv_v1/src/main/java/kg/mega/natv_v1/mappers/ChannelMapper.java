package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.entities.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    Channel channelDtoToChannel (ChannelDto channelDto);
    List<Channel> channelDtoToChannelList (List<ChannelDto> channelDto);
    ChannelDto channelToChannelDto (Channel channel);
    List<ChannelDto> channelToChannelDtoList (List<Channel> channel);


}
