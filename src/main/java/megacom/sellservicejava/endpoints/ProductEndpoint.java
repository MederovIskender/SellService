package megacom.sellservicejava.endpoints;

import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import org.springframework.http.ResponseEntity;

public interface ProductEndpoint {
    ResponseEntity<?> saveProduct(String token, ProductCreateDto productCreateDto);
}
