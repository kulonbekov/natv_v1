package kg.mega.natv_v1.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import kg.mega.natv_v1.models.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    Long id;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date createdDate;
    String clientEmail;
    String clientFio;
    String clientPhone;
    OrderStatus orderStatus;
    double orderSum;
    @JsonProperty(value = "channel")
    ChannelDto channelDto;
    @JsonProperty(value = "text")
    TextDto textDto;
    @JsonProperty(value = "banner")
    BannerDto bannerDto;
}
