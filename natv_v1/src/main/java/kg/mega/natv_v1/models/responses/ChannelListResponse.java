package kg.mega.natv_v1.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelListResponse {

    String channelName;
    @JsonProperty(value = "logo")
    String logoPath;
    @JsonProperty(value = "pricePerLetter")
    double pricePerSymbol;
    @JsonProperty(value = "discounts")
    List<DiscountResponse> discountResponses;
}
