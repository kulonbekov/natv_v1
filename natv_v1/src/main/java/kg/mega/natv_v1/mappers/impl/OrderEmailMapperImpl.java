package kg.mega.natv_v1.mappers.impl;

import kg.mega.natv_v1.mappers.OrderEmailMapper;
import kg.mega.natv_v1.models.responses.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderEmailMapperImpl implements OrderEmailMapper {
    @Override
    public String orderResponseToString(OrderResponse order) {
        String text = "Client FIO: " + order.getClientFIO() +
                "\nClient phone: " + order.getClientPhone() +
                "\nText ad: " + order.getText() +
                "\nTotal price " + order.getTotalPrice();

        return text;
    }
}
