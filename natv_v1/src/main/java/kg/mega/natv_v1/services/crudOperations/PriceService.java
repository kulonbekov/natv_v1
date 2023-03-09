package kg.mega.natv_v1.services.crudOperations;

import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.services.crudOperations.BaseService;

public interface PriceService extends BaseService<PriceDto> {

    Long save(double pricePerLetter, Channel channel);

    Long update(Long priceId, double pricePerLetter, Channel channel);

}
