package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.BannerRep;
import kg.mega.natv_v1.mappers.BannerMapper;
import kg.mega.natv_v1.models.dtos.BannerDto;
import kg.mega.natv_v1.models.entities.Banner;
import kg.mega.natv_v1.services.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRep bannerRep;
    private final BannerMapper bannerMapper = BannerMapper.INSTANCE;
    @Override
    public BannerDto save(BannerDto bannerDto) {
        Banner banner = bannerMapper.bannerDtoToBanner(bannerDto);
        banner = bannerRep.save(banner);
        bannerDto.setId(banner.getId());
        return bannerDto;
    }

    @Override
    public BannerDto findById(Long id) {

        return null;
    }

    @Override
    public List<BannerDto> findAll() {
        return null;
    }

    @Override
    public BannerDto update(BannerDto t) {
        return null;
    }

    @Override
    public BannerDto delete(Long id) {
        return null;
    }
}
