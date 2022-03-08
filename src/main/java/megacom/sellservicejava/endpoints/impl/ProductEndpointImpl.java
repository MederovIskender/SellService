package megacom.sellservicejava.endpoints.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.endpoints.ProductEndpoint;
import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.models.entities.Product;
import megacom.sellservicejava.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductEndpointImpl implements ProductEndpoint {
    ProductService productService;
    AppUserEndPoint appUserEndPoint;

    public ProductEndpointImpl(ProductService productService, AppUserEndPoint appUserEndPoint) {
        this.productService = productService;
        this.appUserEndPoint = appUserEndPoint;
    }

    @Override
    public ResponseEntity<?> saveProduct(String token, ProductCreateDto productCreateDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Product product = productService.saveProduct(productCreateDto);
        if (Objects.isNull(product)){
            return new ResponseEntity<>("Такой продукт уже существует", HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Продукт "+product+" был успешно сохранен");
    }
}
