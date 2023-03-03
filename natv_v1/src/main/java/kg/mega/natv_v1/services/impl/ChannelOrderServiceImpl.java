package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.ChannelOrderRep;
import kg.mega.natv_v1.dao.ChannelRep;
import kg.mega.natv_v1.mappers.ChannelOrderMapper;
import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.entities.ChannelOrder;
import kg.mega.natv_v1.services.ChannelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelOrderServiceImpl implements ChannelOrderService {
    private final ChannelOrderRep channelOrderRep;
    private final ChannelOrderMapper channelOrderMapper = ChannelOrderMapper.INSTANCE;
    private final ChannelRep channelRep;

    @Override
    public ChannelOrderDto save(ChannelOrderDto channelOrderDto) {
        ChannelOrder channelOrder = channelOrderMapper.DtoTo(channelOrderDto);
        channelOrder = channelOrderRep.save(channelOrder);
        channelOrderDto.setId(channelOrder.getId());
        return channelOrderDto;
    }

    @Override
    public ChannelOrderDto findById(Long id) {
        ChannelOrder channelOrder = channelOrderRep.findById(id).orElseThrow(() -> new RuntimeException("ChannelOrder not found"));
        return channelOrderMapper.toDto(channelOrder);
    }

    @Override
    public List<ChannelOrderDto> findAll() {
        return channelOrderMapper.toDtoList(channelOrderRep.findAll());
    }

    @Override
    public ChannelOrderDto update(ChannelOrderDto channelOrderDto) {
        return null;
    }

    @Override
    public ChannelOrderDto delete(Long id) {
        return null;
    }
}
