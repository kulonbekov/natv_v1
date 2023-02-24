package kg.mega.natv_v1.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelDto {
    Long id;
    String channelName;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date createdDate;
    ChannelStatus channelStatus;
    String logoPath;
}
