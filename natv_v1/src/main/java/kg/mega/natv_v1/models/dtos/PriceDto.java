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
public class PriceDto {
    Long id;
    double pricePerSymbol;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date startDate;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date endDate;
    @JsonProperty(value = "channel")
    ChannelDto channelDto;
}
