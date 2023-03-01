package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.PriceMapper;
import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.models.entities.Price;
import kg.mega.natv_v1.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRep priceRep;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;
    @Override
    public PriceDto save(PriceDto priceDto) {
        Price price = priceMapper.priceDtoToPrice(priceDto);
        price = priceRep.save(price);
        priceDto.setId(price.getId());
        return priceDto;
    }

    @Override
    public PriceDto findById(Long id) {
        Price price = priceRep.findById(id).orElseThrow(()->new RuntimeException("Price not found"));
        return priceMapper.priceToPriceDto(price);
    }

    @Override
    public List<PriceDto> findAll() {
        return null;
    }

    @Override
    public PriceDto update(PriceDto t) {
        return null;
    }

    @Override
    public PriceDto delete(Long id) {
        return null;
    }
}
