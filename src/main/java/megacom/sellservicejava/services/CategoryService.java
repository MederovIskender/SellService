package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.categpryDtos.ActualProductPriceDiscountDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryId;
import megacom.sellservicejava.models.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    Category saveCategory(String token, CategoryCreateDto categoryCreateDto);
    Category findCategoryByCategoryName(String name);
}
