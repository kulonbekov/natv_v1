package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.PriceMapper;
import kg.mega.natv_v1.mappers.PriceSaveMapper;
import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Price;
import kg.mega.natv_v1.services.ChannelService;
import kg.mega.natv_v1.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRep priceRep;
    private final ChannelService channelService;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;
    private final PriceSaveMapper priceSaveMapper;

    @Override
    public PriceDto save(PriceDto priceDto) {
        Price price = priceMapper.priceDtoToPrice(priceDto);
        price = priceRep.save(price);
        priceDto.setId(price.getId());
        priceDto.setStartDate(price.getStartDate());
        priceDto.setEndDate(price.getEndDate());
        priceDto.setChannelDto(channelService.findById(priceDto.getId()));
        return priceDto;
    }

    @Override
    public PriceDto findById(Long id) {
        Price price = priceRep.findById(id).orElseThrow(() -> new RuntimeException("Price not found"));
        return priceMapper.priceToPriceDto(price);
    }

    @Override
    public List<PriceDto> findAll() {
        return priceMapper.priceToPriceDtoList(priceRep.findAll());
    }

    @Override
    public PriceDto update(PriceDto t) {
        return null;
    }

    @Override
    public PriceDto delete(Long id) {
        return null;
    }

    @Override
    public Long save(double pricePerLetter, Channel channel) {
        Price price = priceSaveMapper.toPrice(pricePerLetter,channel);
        price = priceRep.save(price);
        return price.getId();
    }

    @Override
    public Long update(Long priceId, double pricePerLetter, Channel channel) {
        Price currentPrice = priceRep.findById(priceId).orElseThrow(()->new RuntimeException("Price not found"));

        if(currentPrice.getPricePerSymbol()==pricePerLetter){
            return priceId;
        }

        currentPrice.setEndDate(new Date());
        priceRep.save(currentPrice);

        Price price = priceSaveMapper.toPrice(pricePerLetter,channel);
        Long newPriceId = priceRep.save(price).getId();

        return newPriceId;
    }
}
