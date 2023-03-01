package kg.mega.natv_v1.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceResponse {

    String text;
    int daysCount;
    Long channelId;
    double price;
    double priceWithDiscount;
}
