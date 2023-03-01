package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.mappers.DiscountMapper;
import kg.mega.natv_v1.models.dtos.DiscountDto;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRep discountRep;
    private final DiscountMapper discountMapper = DiscountMapper.INSTANCE;
    @Override
    public DiscountDto save(DiscountDto discountDto) {
        Discount discount = discountMapper.discountDtoToDiscount(discountDto);
        discount = discountRep.save(discount);
        discountDto.setId(discount.getId());
        return discountDto;
    }

    @Override
    public DiscountDto findById(Long id) {
        return null;
    }

    @Override
    public List<DiscountDto> findAll() {
        return null;
    }

    @Override
    public DiscountDto update(DiscountDto t) {
        return null;
    }

    @Override
    public DiscountDto delete(Long id) {
        return null;
    }
}
