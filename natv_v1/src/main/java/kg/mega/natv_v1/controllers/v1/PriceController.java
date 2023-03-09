package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.dao.PriceRep;
import kg.mega.natv_v1.models.dtos.PriceDto;
import kg.mega.natv_v1.services.crudOperations.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "3. Стоимость")
@RestController
@RequestMapping("/api/v1/price")
@RequiredArgsConstructor
public class PriceController {
    private final PriceService priceService;
    private final PriceRep priceRep;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody PriceDto priceDto) {
        try {
            return new ResponseEntity<>(priceService.save(priceDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск стоимость по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(priceService.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод всех цен на рекламу")
    ResponseEntity<List<PriceDto>> findAll() {
        return ResponseEntity.ok(priceService.findAll());
    }
}
