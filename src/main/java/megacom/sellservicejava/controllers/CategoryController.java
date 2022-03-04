package megacom.sellservicejava.controllers;

import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.models.dtos.CategoryCreateDto;
import megacom.sellservicejava.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestHeader String token, @RequestBody CategoryCreateDto categoryCreateDto){
        return categoryService.saveCategory(categoryCreateDto);
    }
}
