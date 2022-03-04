package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.models.dtos.CategoryCreateDto;
import megacom.sellservicejava.services.CategoryService;
import org.springframework.http.ResponseEntity;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public ResponseEntity<?> saveCategory(CategoryCreateDto categoryCreateDto) {
        return null;
    }
}
