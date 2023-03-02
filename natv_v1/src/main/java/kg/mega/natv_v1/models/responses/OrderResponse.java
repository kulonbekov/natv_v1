package kg.mega.natv_v1.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.mega.natv_v1.models.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {

    String clientEmail;
    String clientFIO;
    String clientPhone;
    String text;
    double totalPrice;
    @JsonProperty(value = "status")
    OrderStatus orderStatus;
    @JsonProperty(value = "channels")
    List<ChannelResponse> channelResponseList;

}
