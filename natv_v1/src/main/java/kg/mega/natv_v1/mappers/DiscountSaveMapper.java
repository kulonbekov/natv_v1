package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.responses.DiscountSaveResponse;

import java.util.List;

public interface DiscountSaveMapper {

    List<Discount> toDiscountList (List<DiscountSaveResponse> discountList, Channel channel);
    List<DiscountSaveResponse> toDiscountSaveList (List<Discount> discounts);
}
