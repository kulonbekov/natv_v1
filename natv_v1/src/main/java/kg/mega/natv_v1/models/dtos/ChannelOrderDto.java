package kg.mega.natv_v1.models.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelOrderDto {
    Long id;
    ChannelDto channelDto;
    OrderDto orderDto;
    int daysCount;
}
