package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.ChannelRep;
import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.PriceMapper;
import kg.mega.natv_v1.mappers.PriceSaveMapper;
import kg.mega.natv_v1.mappers.RequestMapper;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.entities.Price;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import kg.mega.natv_v1.models.responses.ChannelListResponse;
import kg.mega.natv_v1.models.responses.ChannelSaveResponse;
import kg.mega.natv_v1.models.responses.DiscountResponse;
import kg.mega.natv_v1.models.responses.DiscountSaveResponse;
import kg.mega.natv_v1.services.ChannelListService;
import kg.mega.natv_v1.services.DiscountService;
import kg.mega.natv_v1.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelListServiceImpl implements ChannelListService {
    private final ChannelRep channelRep;
    private final PriceRep priceRep;
    private final DiscountRep discountRep;
    private final RequestMapper requestMapper;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;
    private final DiscountService discountService;
    private final PriceSaveMapper priceSaveMapper;
    private final PriceService priceService;

    @Override
    public List<ChannelListResponse> list() {
        List<ChannelListResponse> channelListResponses = new ArrayList<>();
        List<Channel> channels = channelRep.findAllByActive(); // Получить все активные каналы

        for (Channel item : channels) {

            ChannelListResponse channelListResponse = new ChannelListResponse();
            channelListResponse.setChannelName(item.getChannelName());
            channelListResponse.setLogoPath(item.getLogoPath());

            if (priceRep.getPrice(item.getId()) != null) { // Проверить по текущему каналу ,активные цены на рекламу
                if (getDiscount(item.getId()) != null) {  // Проверить по текущему каналу ,активные скидки на рекламу
                    channelListResponse.setPricePerSymbol(priceRep.getPrice(item.getId()).getPricePerSymbol());
                    channelListResponse.setDiscountResponses(getDiscount(item.getId()));

                }
            }
            channelListResponses.add(channelListResponse);
        }
        return channelListResponses;
    }

    @Override
    public ChannelSaveResponse save(ChannelSaveResponse channelDto) {

        Channel channel = requestMapper.channelSaveResponseToChannel(channelDto);
        channel = channelRep.save(channel);
        channelDto.setId(channel.getId());
        channelDto.setChannelStatus(channel.getChannelStatus());

        Price price = priceSaveMapper.toPrice(channelDto.getPricePerSymbol(),channel);
        price = priceRep.save(price);
        PriceDto priceDto = priceMapper.priceToPriceDto(price);
        channelDto.setPriceId(priceDto.getId());

        channelDto.setDiscountSaveResponses(discountService.saveAll(channelDto.getDiscountSaveResponses(),channel));

        return channelDto;
    }

    @Override
    public ChannelSaveResponse update(ChannelSaveResponse channelDto) {

        Channel channel = channelRep.findById(channelDto.getId()).orElseThrow(()->new RuntimeException("Channel not found"));
        channel = requestMapper.channelSaveResponseToChannel(channelDto);
        channel = channelRep.save(channel);

        Long priceId = priceService.update(channelDto.getPriceId(),channelDto.getPricePerSymbol(),channel);
        channelDto.setPriceId(priceId);

        List<DiscountSaveResponse> discountDtos = discountService.update(channelDto.getDiscountSaveResponses(), channel);
        channelDto.setDiscountSaveResponses(discountDtos);

        return channelDto;
    }


    private List<DiscountResponse> getDiscount(Long id) { //Проверить и получить по текущему каналу ,активные скидки на рекламу
        List<Discount> discounts = discountRep.getDiscounts(id);
        List<DiscountResponse> newDiscounts = new ArrayList<>();

        for (Discount item : discounts) {
            if (item.getStartDate().before(new Date()) &&
                    item.getEndDate().after(new Date())) {
                DiscountResponse discountResponse = new DiscountResponse();
                discountResponse.setDiscount(item.getDiscount());
                discountResponse.setDiscountDays(item.getDiscountDays());
                newDiscounts.add(discountResponse);
            }
        }
        return newDiscounts;
    }
}
