package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.OrderDatesRep;
import kg.mega.natv_v1.mappers.OrderDatesMapper;
import kg.mega.natv_v1.models.dtos.OrderDatesDto;
import kg.mega.natv_v1.models.entities.OrderDates;
import kg.mega.natv_v1.services.OrderDatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDatesServiceImpl implements OrderDatesService {
    private final OrderDatesRep orderDatesRep;
    private final OrderDatesMapper orderDatesMapper = OrderDatesMapper.INSTANCE;

    @Override
    public OrderDatesDto save(OrderDatesDto orderDatesDto) {
        OrderDates orderDates = orderDatesMapper.orderDatesDtoToOrderDates(orderDatesDto);
        orderDates = orderDatesRep.save(orderDates);
        orderDatesDto.setId(orderDates.getId());
        return orderDatesDto;
    }

    @Override
    public OrderDatesDto findById(Long id) {
        OrderDates orderDates = orderDatesRep.findById(id).orElseThrow(()->new RuntimeException("Order dates no found"));
        return orderDatesMapper.orderDatesToOrderDatesDto(orderDates);
    }

    @Override
    public List<OrderDatesDto> findAll() {
        return null;
    }

    @Override
    public OrderDatesDto update(OrderDatesDto t) {
        return null;
    }

    @Override
    public OrderDatesDto delete(Long id) {
        return null;
    }
}
