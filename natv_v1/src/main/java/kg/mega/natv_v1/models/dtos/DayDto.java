package kg.mega.natv_v1.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DayDto {
    Long id;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonProperty(value = "days")
    Date date;
    @JsonProperty(value = "Dates")
    ChannelOrderDto channelOrderDto;
}
