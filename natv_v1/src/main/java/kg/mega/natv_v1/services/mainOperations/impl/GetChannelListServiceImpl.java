package kg.mega.natv_v1.services.mainOperations.impl;

import kg.mega.natv_v1.dao.ChannelRep;
import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.mainMapper.OrderSaveMapper;
import kg.mega.natv_v1.models.entities.Channel;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.responses.ChannelListResponse;
import kg.mega.natv_v1.models.responses.ChannelSaveResponse;
import kg.mega.natv_v1.models.responses.DiscountResponse;
import kg.mega.natv_v1.models.responses.DiscountSaveResponse;
import kg.mega.natv_v1.services.crudOperations.DiscountService;
import kg.mega.natv_v1.services.crudOperations.PriceService;
import kg.mega.natv_v1.services.mainOperations.GetChannelListService;
import kg.mega.natv_v1.services.mainOperations.GetCostAdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetChannelListServiceImpl implements GetChannelListService {
    private final ChannelRep channelRep;
    private final PriceRep priceRep;
    private final DiscountRep discountRep;
    private final OrderSaveMapper orderSaveMapper;
    private final DiscountService discountService;
    private final PriceService priceService;
    private final GetCostAdsService getCostAdsService;

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
                    channelListResponse.setPricePerSymbol(getCostAdsService.getPrice(item.getId()));
                    channelListResponse.setDiscountResponses(getDiscount(item.getId()));

                }
            }
            channelListResponses.add(channelListResponse);
        }
        return channelListResponses;
    }

    @Override
    public ChannelSaveResponse save(ChannelSaveResponse channelDto) {

        Channel channel = orderSaveMapper.channelSaveResponseToChannel(channelDto);
        channel = channelRep.save(channel);
        channelDto.setId(channel.getId());
        channelDto.setChannelStatus(channel.getChannelStatus());

        Long priceId = priceService.save(channelDto.getPricePerSymbol(), channel);
        channelDto.setPriceId(priceId);

        channelDto.setDiscountSaveResponses(discountService.saveAll(channelDto.getDiscountSaveResponses(), channel));

        return channelDto;
    }

    @Override
    public ChannelSaveResponse update(ChannelSaveResponse channelDto) {

        Channel channel = channelRep.findById(channelDto.getId()).orElseThrow(() -> new RuntimeException("Channel not found"));
        channel = orderSaveMapper.channelSaveResponseToChannel(channelDto);
        channel = channelRep.save(channel);

        Long priceId = priceService.update(channelDto.getPriceId(), channelDto.getPricePerSymbol(), channel);
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
