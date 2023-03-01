package kg.mega.natv_v1.models.requests;

import kg.mega.natv_v1.models.dtos.ChannelDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceRequest {

    String text;
    int daysCount;
    Long channelId;
}
