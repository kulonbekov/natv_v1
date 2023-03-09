package kg.mega.natv_v1.services.mainOperations;

import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;

public interface GetCostAdsService {

    PriceResponse calculate(PriceRequest priceRequest);
}
