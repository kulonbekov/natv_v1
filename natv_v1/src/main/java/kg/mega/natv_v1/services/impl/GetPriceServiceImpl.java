package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.RequestMapper;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;
import kg.mega.natv_v1.services.ChannelService;
import kg.mega.natv_v1.services.GetPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPriceServiceImpl implements GetPriceService {
    private final ChannelService channelService;
    private final PriceRep priceRep;
    private final DiscountRep discountRep;
    private final RequestMapper requestMapper;

    @Override
    public PriceResponse getPrice(PriceRequest priceRequest) {

        List<Discount> discounts = discountRep.getDiscounts(priceRequest.getChannelId());
        int discount = getDiscount(discounts, priceRequest);

        int symbolCount = (priceRequest.getText().replaceAll(" ", "").length());
        double price = priceRep.getPrice(priceRequest.getChannelId()).getPricePerSymbol() * symbolCount * priceRequest.getDaysCount();
        double priceWithDiscount = price - ((price * discount) / 100);

        return requestMapper.requestToResponse(priceRequest, price, priceWithDiscount);

    }

    private int getDiscount(List<Discount> discounts, PriceRequest priceRequest) {
        int discount = 0;

        for (Discount item : discounts) {
            if (item.getStartDate().before(new Date()) &&
                    item.getEndDate().after(new Date()) &&
                    item.getDiscountDays() <= priceRequest.getDaysCount()) {
                discount = item.getDiscount();
            }
        }
        return discount;
    }
}