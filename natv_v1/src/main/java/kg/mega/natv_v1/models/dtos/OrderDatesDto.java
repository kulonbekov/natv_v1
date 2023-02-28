package kg.mega.natv_v1.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDatesDto {
    Long id;
    @JsonProperty(value = "days")
    Date date;
    @JsonProperty(value = "order")
    OrderDto orderDto;
}
