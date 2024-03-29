package kg.mega.natv_v1.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.natv_v1.models.dtos.TextDto;
import kg.mega.natv_v1.services.crudOperations.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "6. Тексты реклам")
@RestController
@RequestMapping("/api/v2/text")
@RequiredArgsConstructor
public class TextController {
    private final TextService textService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody TextDto textDto) {
        try {
            return new ResponseEntity<>(textService.save(textDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск текста по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(textService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод всех рекламных текстов")
    ResponseEntity<List<TextDto>> findAll() {
        return ResponseEntity.ok(textService.findAll());
    }
}
