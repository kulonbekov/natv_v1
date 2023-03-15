package kg.mega.natv_v1.services.mainOperations.impl;

import kg.mega.natv_v1.dao.DiscountRep;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.mappers.mainMapper.OrderSaveMapper;
import kg.mega.natv_v1.models.entities.Discount;
import kg.mega.natv_v1.models.entities.Price;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;
import kg.mega.natv_v1.services.mainOperations.GetCostAdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCostAdsServiceImpl implements GetCostAdsService {
    private final PriceRep priceRep;
    private final DiscountRep discountRep;
    private final OrderSaveMapper orderSaveMapper;

    @Override
    public PriceResponse calculate(PriceRequest priceRequest) {


        int discount = getDiscount(priceRequest); // Проверка для получение скидок по заданному количеству дней

        int symbolCount = (priceRequest.getText().replaceAll(" ", "").length()); // Подсчитать количество символов в тексте обьявления
        double price = getPrice(priceRequest.getChannelId()) * symbolCount * priceRequest.getDaysCount(); // Подсчитать общую сумму без скидки
        double priceWithDiscount = price - ((price * discount) / 100); // Подсчитать общую сумму со скидкой
        String text = getFormattedText(priceRequest.getText());
        priceRequest.setText(text);

        return orderSaveMapper.requestToResponse(priceRequest, price, priceWithDiscount); //Возвращает json "PriceResponse"

    }

    private int getDiscount(PriceRequest priceRequest) { //Получить из базы текущие скидки по каналу и получить актуальную скидку по заданному количеству дней

        List<Discount> discounts = discountRep.getDiscounts(priceRequest.getChannelId()); // Получить актульаные скидки по каналу
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

    @Override
    public double getPrice(Long id) { //Получить актуальную цену на рекламу
        double price = 0.0;
        List<Price> prices = priceRep.getPrice(id);

        for (Price item : prices) {
            if (item.getStartDate().before(new Date()) &&
                    item.getEndDate().after(new Date())) {
                price = item.getPricePerSymbol();
            }
        }
        return price;
    }

    private String getFormattedText(String text){
        return text.trim().replaceAll("\\s++"," ");
    }
}
