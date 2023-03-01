package kg.mega.natv_v1.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    Long id;
    String clientEmail;
    String clientFIO;
    String clientPhone;
    String text;
    @JsonProperty(value = "channels")
    ChannelRequest channelRequest;
}
