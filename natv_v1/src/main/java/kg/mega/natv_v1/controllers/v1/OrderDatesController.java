package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.models.dtos.BannerDto;
import kg.mega.natv_v1.models.dtos.OrderDatesDto;
import kg.mega.natv_v1.services.OrderDatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Даты заказа")
@RestController
@RequestMapping("/api/v1/order/dates")
@RequiredArgsConstructor
public class OrderDatesController {

    private final OrderDatesService orderDatesService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody OrderDatesDto orderDatesDto){
        try{
            return new ResponseEntity<>(orderDatesService.save(orderDatesDto), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
