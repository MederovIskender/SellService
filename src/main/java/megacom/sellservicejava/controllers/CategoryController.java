package megacom.sellservicejava.controllers;

import megacom.sellservicejava.endpoints.CategoryEndpoint;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    CategoryEndpoint categoryEndpoint;

    public CategoryController(CategoryEndpoint categoryEndpoint) {
        this.categoryEndpoint = categoryEndpoint;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestHeader String token, @RequestBody CategoryCreateDto categoryCreateDto){
        return categoryEndpoint.saveCategory(token, categoryCreateDto);
    }
}
