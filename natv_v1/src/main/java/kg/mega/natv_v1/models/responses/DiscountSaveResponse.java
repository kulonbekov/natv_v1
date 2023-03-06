package kg.mega.natv_v1.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountSaveResponse {

    Long id;
    int discount;
    @JsonProperty(value = "from_days_count")
    int discountDays;



}
