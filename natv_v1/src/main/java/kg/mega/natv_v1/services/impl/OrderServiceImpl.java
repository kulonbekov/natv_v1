package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.OrderRep;
import kg.mega.natv_v1.mappers.OrderMapper;
import kg.mega.natv_v1.models.dtos.OrderDto;
import kg.mega.natv_v1.models.entities.Order;
import kg.mega.natv_v1.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRep orderRep;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        order = orderRep.save(order);
        orderDto.setId(order.getId());
        return orderDto;
    }

    @Override
    public OrderDto findById(Long id) {
        return null;
    }

    @Override
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public OrderDto update(OrderDto t) {
        return null;
    }

    @Override
    public OrderDto delete(Long id) {
        return null;
    }
}
