package kg.mega.natv_v1.services;

import kg.mega.natv_v1.models.responses.ChannelListResponse;

import java.util.List;

public interface ChannelListService {
    List<ChannelListResponse> list();
}
