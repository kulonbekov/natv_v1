package kg.mega.natv_v1.mappers.impl;

import kg.mega.natv_v1.mappers.PriceSaveMapper;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Price;
import kg.mega.natv_v1.models.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PriceSaveMapperImpl implements PriceSaveMapper {
    @Override
    public Price toPrice(double price, Channel channel) {

        Price newPrice = new Price();
        newPrice.setPricePerSymbol(price);
        newPrice.setChannel(channel);
        newPrice.setStartDate(new Date());
        newPrice.setEndDate(DateUtil.getINSTANCE().getEndDate());
        return newPrice;
    }
}
