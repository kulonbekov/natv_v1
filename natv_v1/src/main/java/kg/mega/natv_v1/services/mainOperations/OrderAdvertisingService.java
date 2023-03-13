package kg.mega.natv_v1.services.mainOperations;

import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.responses.OrderResponse;

public interface OrderAdvertisingService {

    OrderResponse CreateAdvertising(OrderRequest orderRequest);
    //void settingEmail(OrderResponse orderResponse);
}
