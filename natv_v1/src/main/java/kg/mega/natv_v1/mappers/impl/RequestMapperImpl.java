package kg.mega.natv_v1.mappers.impl;

import kg.mega.natv_v1.mappers.RequestMapper;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.dtos.TextDto;
import kg.mega.natv_v1.models.entities.Order;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;
import org.springframework.stereotype.Service;

@Service
public class RequestMapperImpl implements RequestMapper {
    @Override
    public Order orderRequestToOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setClientEmail(orderRequest.getClientEmail());
        order.setClientFIO(orderRequest.getClientFIO());
        order.setClientPhone(orderRequest.getClientPhone());

        return null;
    }

    @Override
    public PriceResponse requestToResponse(PriceRequest priceRequest, double price, double priceWithDiscount) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setText(priceRequest.getText());
        priceResponse.setDaysCount(priceRequest.getDaysCount());
        priceResponse.setChannelId(priceRequest.getChannelId());
        priceResponse.setPrice(price);
        priceResponse.setPriceWithDiscount(priceWithDiscount);
        return priceResponse;
    }
}
