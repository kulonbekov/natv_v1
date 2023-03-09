package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.models.dtos.DiscountDto;
import kg.mega.natv_v1.services.crudOperations.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "4. Скидки")
@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody DiscountDto discountDto) {
        try {
            return new ResponseEntity<>(discountService.save(discountDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск скидки по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(discountService.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод всех сикдок на рекламу")
    ResponseEntity<List<DiscountDto>> findAll() {
        return ResponseEntity.ok(discountService.findAll());
    }

}
