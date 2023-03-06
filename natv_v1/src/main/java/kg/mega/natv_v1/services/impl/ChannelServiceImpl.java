package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.ChannelRep;
import kg.mega.natv_v1.mappers.ChannelMapper;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import kg.mega.natv_v1.services.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRep channelRep;
    private final ChannelMapper channelMapper = ChannelMapper.INSTANCE;

    @Override
    public ChannelDto save(ChannelDto channelDto) {
        Channel channel = channelMapper.channelDtoToChannel(channelDto);
        channel = channelRep.save(channel);
        channelDto.setId(channel.getId());
        return channelDto;
    }

    @Override
    public ChannelDto findById(Long id) {
        Channel channel = channelRep.findById(id).orElseThrow(() -> new RuntimeException("Channel not found"));
        return channelMapper.channelToChannelDto(channel);
    }

    @Override
    public List<ChannelDto> findAll() {
        return channelMapper.channelToChannelDtoList(channelRep.findAll());
    }

    @Override
    public ChannelDto update(ChannelDto t) {
        return null;
    }

    @Override
    public ChannelDto delete(Long id) {
        ChannelDto channelDto = findById(id);
        channelDto.setChannelStatus(ChannelStatus.FALSE);
        return save(channelDto);
    }
}
