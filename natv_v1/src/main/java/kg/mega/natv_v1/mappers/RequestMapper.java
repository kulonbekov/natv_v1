package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.dtos.TextDto;
import kg.mega.natv_v1.models.entities.ChannelOrder;
import kg.mega.natv_v1.models.entities.Order;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;
import org.mapstruct.factory.Mappers;

public interface RequestMapper {

    Order orderRequestToOrder (OrderRequest orderRequest);
    PriceResponse requestToResponse (PriceRequest priceRequest, double price, double priceWithDiscount);
}
