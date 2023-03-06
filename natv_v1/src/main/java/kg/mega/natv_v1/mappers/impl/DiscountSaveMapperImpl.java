package kg.mega.natv_v1.mappers.impl;

import kg.mega.natv_v1.mappers.DiscountSaveMapper;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.responses.DiscountSaveResponse;
import kg.mega.natv_v1.models.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountSaveMapperImpl implements DiscountSaveMapper {
    @Override
    public List<Discount> toDiscountList(List<DiscountSaveResponse> discounts, Channel channel) {
        List<Discount> discountList = discounts.stream()
                .map(item->getNewDiscount(item, channel))
                .collect(Collectors.toList());
        return discountList;
    }

    @Override
    public List<DiscountSaveResponse> toDiscountSaveList(List<Discount> discounts) {
        List<DiscountSaveResponse> discountList = discounts.stream()
                .map(item->toDiscountSave(item))
                .collect(Collectors.toList());
        return discountList;
    }

    private Discount getNewDiscount(DiscountSaveResponse item, Channel channel){

        Discount discount = new Discount();
        discount.setId(item.getId());
        discount.setDiscountDays(item.getDiscountDays());
        discount.setChannel(channel);
        discount.setDiscount(item.getDiscount());

        discount.setStartDate(new Date());
        discount.setEndDate(DateUtil.getINSTANCE().getEndDate());
        return discount;
    }
    private DiscountSaveResponse toDiscountSave (Discount item){
        DiscountSaveResponse discountDto = new DiscountSaveResponse();
        discountDto.setId(item.getId());
        discountDto.setDiscountDays(item.getDiscountDays());
        discountDto.setDiscount(item.getDiscount());
        return discountDto;
    }
}
