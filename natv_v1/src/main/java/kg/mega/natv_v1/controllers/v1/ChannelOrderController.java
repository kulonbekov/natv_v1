package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.dtos.ChannelOrderDto;
import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.services.ChannelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "8. Канал и Заказ")
@RestController
@RequestMapping("/api/v1/channelOrder")
@RequiredArgsConstructor
public class ChannelOrderController {

    private final ChannelOrderService channelOrderService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody ChannelOrderDto channelOrderDto){
        try{
            return new ResponseEntity<>(channelOrderService.save(channelOrderDto), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск канала и заказа по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(channelOrderService.findById(id), HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/all")
    @ApiOperation("Вывод всех каналов и заказов")
    ResponseEntity<List<ChannelOrderDto>> findAll(){
        return ResponseEntity.ok(channelOrderService.findAll());
    }
}
