package megacom.sellservicejava.endpoints;

import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryId;
import org.springframework.http.ResponseEntity;

public interface CategoryEndpoint {
    ResponseEntity<?> saveCategory(String token, CategoryCreateDto categoryCreateDto);

    ResponseEntity<?> getActualInfo(String token, long categoryId);
}
