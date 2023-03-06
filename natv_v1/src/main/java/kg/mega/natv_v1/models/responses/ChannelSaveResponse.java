package kg.mega.natv_v1.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelSaveResponse {
    Long id;
    @JsonProperty(value = "name")
    String channelName;
    @JsonIgnore
    Date createdDate;
    @JsonProperty(value = "active")
    @Enumerated(EnumType.STRING)
    ChannelStatus channelStatus;
    @JsonProperty(value = "logo")
    String logoPath;
    @JsonIgnore
    Long priceId;
    @JsonProperty(value = "price_per_letter")
    double pricePerSymbol;
    @JsonProperty(value = "discounts")
    List<DiscountSaveResponse> discountSaveResponses;


}
