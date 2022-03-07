package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.entities.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> saveCategory(String token, CategoryCreateDto categoryCreateDto);
    Category findCategoryByCategoryName(String name);
}
