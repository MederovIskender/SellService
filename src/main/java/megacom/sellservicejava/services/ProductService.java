package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.categpryDtos.ActualProductPriceDiscountDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductEntityDto;
import megacom.sellservicejava.models.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    Product saveProduct(ProductCreateDto productCreateDto);
    Product findProductByName(String name);
    Product findProductById(long id);
    List<Product>findAllProductsByCategory(long categoryId);

   Product findProductByBarcode(String barcode);
}
