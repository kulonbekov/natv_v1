package kg.mega.natv_v1.services.mainOperations;

import kg.mega.natv_v1.models.responses.ChannelListResponse;
import kg.mega.natv_v1.models.responses.ChannelSaveResponse;

import java.util.List;

public interface GetChannelListService {
    List<ChannelListResponse> list();
    ChannelSaveResponse save(ChannelSaveResponse channelDto);
    ChannelSaveResponse update(ChannelSaveResponse channelDto);
}
