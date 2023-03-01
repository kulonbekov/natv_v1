package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.models.dtos.ChannelDto;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import kg.mega.natv_v1.models.requests.PriceRequest;
import kg.mega.natv_v1.services.ChannelService;
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

    @PostMapping("/get/price")
    @ApiOperation("Получить стоимости рекламы на одном канале")
    ResponseEntity<?> findById(@RequestBody PriceRequest priceRequest) {
        ChannelDto channelDto = channelService.findById(priceRequest.getChannelId());
        if(channelDto!=null && channelDto.getChannelStatus().equals(ChannelStatus.TRUE)) {
            try {
                return ResponseEntity.ok(getPriceService.getPrice(priceRequest));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }else{
            return ResponseEntity.status(400).body("Channel not found");
        }
    }
}
