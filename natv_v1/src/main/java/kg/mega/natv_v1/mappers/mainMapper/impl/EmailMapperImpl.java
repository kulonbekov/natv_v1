package kg.mega.natv_v1.mappers.mainMapper.impl;

import kg.mega.natv_v1.mappers.mainMapper.EmailMapper;
import kg.mega.natv_v1.models.responses.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class EmailMapperImpl implements EmailMapper {
    @Override
    public String orderResponseToString(OrderResponse order) {
        String text = "КОД ОПЛАТЫ: " + getRandom() +
                "\nФИО: " + order.getClientFIO() +
                "\nТЕЛЕФОН: " + order.getClientPhone() +
                "\nТЕКСТ ОБЪЯВЛЕНИЯ: " + order.getText() +
                "\nСТОИМОСТЬ ОБЪЯВЛЕНИЯ " + order.getTotalPrice();

        return text;
    }

    private Integer getRandom(){ // Генерация случайных чисел от 100000 до 999999
        int a = (int) (Math.random() * (999999-100000)+100000 );
        return a;
    }
}
