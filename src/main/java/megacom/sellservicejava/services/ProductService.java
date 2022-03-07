package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> saveProduct(String token, ProductCreateDto productCreateDto);
}
