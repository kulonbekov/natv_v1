package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Price;

public interface PriceSaveMapper {

    Price toPrice (double price, Channel channel);
}
