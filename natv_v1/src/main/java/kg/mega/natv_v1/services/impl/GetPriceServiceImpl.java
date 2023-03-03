package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.RequestMapper;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;
import kg.mega.natv_v1.services.GetPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPriceServiceImpl implements GetPriceService {
    private final PriceRep priceRep;
    private final DiscountRep discountRep;
    private final RequestMapper requestMapper;

    @Override
    public PriceResponse calculate(PriceRequest priceRequest) {

        List<Discount> discounts = discountRep.getDiscounts(priceRequest.getChannelId()); // Получить актульаные скидки по каналу
        int discount = getDiscount(discounts, priceRequest); // Проверка для получение скидик по заданному количеству дней

        int symbolCount = (priceRequest.getText().replaceAll(" ", "").length()); // Подсчитать количество символов в тексте обьявления
        double price = priceRep.getPrice(priceRequest.getChannelId()).getPricePerSymbol() * symbolCount * priceRequest.getDaysCount(); // Подсчитать общую сумму без скидки
        double priceWithDiscount = price - ((price * discount) / 100); // Подсчитать общую сумму со скидкой

        return requestMapper.requestToResponse(priceRequest, price, priceWithDiscount); //Возвращает json "PriceResponse"

    }

    private int getDiscount(List<Discount> discounts, PriceRequest priceRequest) { //Получить из базы текущие скидки по каналу и получить актуальную скидку по заданному количеству дней
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
