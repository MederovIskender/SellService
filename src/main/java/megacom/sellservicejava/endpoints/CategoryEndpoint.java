package megacom.sellservicejava.endpoints;

import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import org.springframework.http.ResponseEntity;

public interface CategoryEndpoint {
    ResponseEntity<?> saveCategory(String token, CategoryCreateDto categoryCreateDto);
}
