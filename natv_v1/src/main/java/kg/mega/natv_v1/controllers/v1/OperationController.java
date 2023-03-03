package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.dao.OrderRep;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.ChannelListResponse;
import kg.mega.natv_v1.services.ChannelListService;
import kg.mega.natv_v1.services.ChannelService;
import kg.mega.natv_v1.services.CreateAdService;
import kg.mega.natv_v1.services.GetPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Операции")
@RestController
@RequestMapping("/api/v1/operation")
@RequiredArgsConstructor
public class OperationController {

    private final GetPriceService getPriceService;
    private final ChannelService channelService;
    private final CreateAdService createAdService;
    private final ChannelListService channelListService;

    @PostMapping("/calculate")
    @ApiOperation("Получить стоимости рекламы на одном канале")
    ResponseEntity<?> calculate(@RequestBody PriceRequest priceRequest) {
        ChannelDto channelDto = null;
        try {
            channelDto = channelService.findById(priceRequest.getChannelId());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Channel not found");
        }
        if (channelDto.getChannelStatus().equals(ChannelStatus.TRUE)) {
            try {
                return ResponseEntity.ok(getPriceService.calculate(priceRequest));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(400).body("Channel is not active");
        }
    }
    @PostMapping("/createAd")
    @ApiOperation("Создание заявку на рекламу")
    ResponseEntity<?> save(@RequestBody OrderRequest orderRequest){
        try{
            return ResponseEntity.ok(createAdService.newCreateAd(orderRequest));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/list")
    @ApiOperation("Получения списка актуальный каналов")
    ResponseEntity<?> list() {
        try{
            return ResponseEntity.ok(channelListService.list());
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
