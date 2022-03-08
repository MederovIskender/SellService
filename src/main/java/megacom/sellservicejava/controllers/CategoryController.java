package megacom.sellservicejava.controllers;

import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestHeader String token, @RequestBody CategoryCreateDto categoryCreateDto){
        return categoryService.saveCategory(token, categoryCreateDto);
    }
}
