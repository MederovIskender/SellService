package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.models.entities.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Product saveProduct(ProductCreateDto productCreateDto);
    Product findProductByName(String name);
    Product findProductById(long id);
}
