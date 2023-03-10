package kg.mega.natv_v1.services.crudOperations;

import kg.mega.natv_v1.models.dtos.DiscountDto;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.responses.DiscountSaveResponse;
import kg.mega.natv_v1.services.crudOperations.BaseService;

import java.util.List;

public interface DiscountService extends BaseService<DiscountDto> {

    List<DiscountSaveResponse> saveAll(List<DiscountSaveResponse> discounts, Channel channel);
    List<DiscountSaveResponse> update(List<DiscountSaveResponse> discounts, Channel channel);
}
