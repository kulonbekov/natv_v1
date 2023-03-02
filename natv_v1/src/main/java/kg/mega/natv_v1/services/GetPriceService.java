package kg.mega.natv_v1.services;

import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;

public interface GetPriceService {

    PriceResponse calculate(PriceRequest priceRequest);
}
