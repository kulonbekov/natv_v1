package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.DiscountDto;
import kg.mega.natv_v1.models.entities.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    @Mapping(source = "channelDto", target = "channel")
    Discount discountDtoToDiscount(DiscountDto discountDto);

    List<Discount> discountDtoToDiscountList(List<DiscountDto> discountDto);

    @Mapping(source = "channel", target = "channelDto")
    DiscountDto discountToDiscountDto(Discount discount);

    List<DiscountDto> discountToDiscountDtoList(List<Discount> discount);


}
