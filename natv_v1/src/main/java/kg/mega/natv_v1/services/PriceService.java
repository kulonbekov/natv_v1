package kg.mega.natv_v1.services;

import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.models.entities.Channel;

public interface PriceService extends BaseService<PriceDto>{

    Long update(Long priceId, double pricePerLetter, Channel channel);
}
