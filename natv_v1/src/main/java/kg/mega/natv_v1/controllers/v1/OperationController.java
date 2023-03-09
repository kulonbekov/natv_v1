package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import kg.mega.natv_v1.models.requests.OrderRequest;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.models.responses.ChannelSaveResponse;
import kg.mega.natv_v1.services.mainOperations.GetChannelListService;
import kg.mega.natv_v1.services.crudOperations.ChannelService;
import kg.mega.natv_v1.services.mainOperations.AdvertisingRequestService;
import kg.mega.natv_v1.services.mainOperations.GetCostAdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "1. Операции")
@RestController
@RequestMapping("/api/v1/operation")
@RequiredArgsConstructor
public class OperationController {

    private final GetCostAdsService getCostAdsService;
    private final ChannelService channelService;
    private final AdvertisingRequestService advertisingRequestService;
    private final GetChannelListService getChannelListService;

    @PostMapping ("/calculate")
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
                return ResponseEntity.ok(getCostAdsService.calculate(priceRequest));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(400).body("Channel is not active");
        }
    }

    @PostMapping("/createAd")
    @ApiOperation("Создание заявки на рекламу")
    ResponseEntity<?> save(@RequestBody OrderRequest orderRequest) {
        try {
            return ResponseEntity.ok(advertisingRequestService.newCreateAd(orderRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    @ApiOperation("Получения списка актуальный каналов")
    ResponseEntity<?> list() {
        try {
            return ResponseEntity.ok(getChannelListService.list());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/save")
    @ApiOperation("Создание канала")
    ResponseEntity<?> save (@RequestBody ChannelSaveResponse channelDto){
        try {
            return ResponseEntity.ok(getChannelListService.save(channelDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping ("/update")
    @ApiOperation("Редактирование канала")
    ResponseEntity<?> update (@RequestBody ChannelSaveResponse channelDto){
        try {
            return ResponseEntity.ok(getChannelListService.update(channelDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
