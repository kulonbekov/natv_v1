package kg.mega.natv_v1.services.crudOperations.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.mappers.DiscountMapper;
import kg.mega.natv_v1.mappers.mainMapper.DiscountSaveMapper;
import kg.mega.natv_v1.models.dtos.DiscountDto;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.responses.DiscountSaveResponse;
import kg.mega.natv_v1.models.utils.DateUtil;
import kg.mega.natv_v1.services.crudOperations.ChannelService;
import kg.mega.natv_v1.services.crudOperations.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRep discountRep;
    private final ChannelService channelService;
    private final DiscountMapper discountMapper = DiscountMapper.INSTANCE;
    private final DiscountSaveMapper discountSaveMapper;

    @Override
    public DiscountDto save(DiscountDto discountDto) {
        Discount discount = discountMapper.discountDtoToDiscount(discountDto);
        discount = discountRep.save(discount);
        discountDto.setId(discount.getId());
        discountDto.setStartDate(discount.getStartDate());
        discountDto.setEndDate(discount.getEndDate());
        discountDto.setChannelDto(channelService.findById(discountDto.getChannelDto().getId()));
        return discountDto;
    }

    @Override
    public DiscountDto findById(Long id) {
        Discount discount = discountRep.findById(id).orElseThrow(() -> new RuntimeException("Discount not found"));
        return discountMapper.discountToDiscountDto(discount);
    }

    @Override
    public List<DiscountDto> findAll() {
        return discountMapper.discountToDiscountDtoList(discountRep.findAll());
    }

    @Override
    public DiscountDto update(DiscountDto t) {
        return null;
    }

    @Override
    public DiscountDto delete(Long id) {
        return null;
    }

    @Override
    public List<DiscountSaveResponse> saveAll(List<DiscountSaveResponse> discounts, Channel channel) {
        List<Discount> discountList = discountSaveMapper.toDiscountList(discounts,channel);
        discountList = discountRep.saveAll(discountList);
        return discountSaveMapper.toDiscountSaveList(discountList);
    }

    @Override
    public List<DiscountSaveResponse> update(List<DiscountSaveResponse> discounts, Channel channel) {

        List<Discount> currentDiscounts = discountRep.findAllByChannelAndEndDate(channel, DateUtil.getINSTANCE().getEndDate());
        List<Discount> discountForDelete = new ArrayList<>();

        for(Discount discount: currentDiscounts){
            for(DiscountSaveResponse discountDto: discounts){
                if(!discount.getId().equals(discountDto.getId())){
                    discount.setEndDate(new Date());
                    discountForDelete.add(discount);
                    break;
                }
            }
        }
        discountRep.saveAll(discountForDelete);
        return saveAll(discounts, channel);
    }


}
