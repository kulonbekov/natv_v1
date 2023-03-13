package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "channelDto", target = "channel")
    Price priceDtoToPrice(PriceDto priceDto);
    List<Price> priceDtoToPrice(List<PriceDto> priceDto);
    @Mapping(source = "channel", target = "channelDto")
    PriceDto priceToPriceDto(Price price);
    List<PriceDto> priceToPriceDtoList(List<Price> price);

}
