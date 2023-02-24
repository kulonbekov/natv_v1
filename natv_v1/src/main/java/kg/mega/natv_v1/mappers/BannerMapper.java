package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.BannerDto;
import kg.mega.natv_v1.models.entities.Banner;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface BannerMapper {
    BannerMapper INSTANCE = Mappers.getMapper(BannerMapper.class);

    Banner bannerDtoToBanner (BannerDto bannerDto);
    List<Banner> bannerDtoToBannerList (List<BannerDto> bannerDto);
    BannerDto bannerToBannerDto (Banner banner);
    List<BannerDto> bannerToBannerDtoList (List<Banner> banner);

}
