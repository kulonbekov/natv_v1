package kg.mega.natv_v1.services;

import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.PriceResponse;
import org.springframework.http.ResponseEntity;

public interface GetPriceService {

    PriceResponse getPrice (PriceRequest priceRequest);
}
