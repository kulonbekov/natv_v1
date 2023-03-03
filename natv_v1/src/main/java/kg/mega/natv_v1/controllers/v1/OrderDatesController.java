package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.models.dtos.OrderDatesDto;
import kg.mega.natv_v1.services.OrderDatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "9. Даты заказов")
@RestController
@RequestMapping("/api/v1/order/dates")
@RequiredArgsConstructor
public class OrderDatesController {

    private final OrderDatesService orderDatesService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody OrderDatesDto orderDatesDto) {
        try {
            return new ResponseEntity<>(orderDatesService.save(orderDatesDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск даты заказа по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(orderDatesService.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод всех дат по всем рекламам")
    ResponseEntity<List<OrderDatesDto>> findAll() {
        return ResponseEntity.ok(orderDatesService.findAll());
    }

}
