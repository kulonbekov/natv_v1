package kg.mega.natv_v1.services;

import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.responses.ChannelListResponse;
import kg.mega.natv_v1.models.responses.ChannelSaveResponse;

import java.util.List;

public interface ChannelListService {
    List<ChannelListResponse> list();

    ChannelSaveResponse save(ChannelSaveResponse channelDto);
}
