package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.models.entities.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> saveProduct(String token, ProductCreateDto productCreateDto);
    Product findProductByName(String name);
}
