package kg.mega.natv_v1.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelRequest {

    Long channelId;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    List<Date> dateList;
}
