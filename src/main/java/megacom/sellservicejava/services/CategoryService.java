package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.CategoryCreateDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> saveCategory(CategoryCreateDto categoryCreateDto);
}
