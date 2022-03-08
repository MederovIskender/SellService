package megacom.sellservicejava.controllers;

import megacom.sellservicejava.endpoints.ProductEndpoint;
import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    ProductEndpoint productEndpoint;

    public ProductController(ProductEndpoint productEndpoint) {
        this.productEndpoint = productEndpoint;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestHeader String token, @RequestBody ProductCreateDto productCreateDto){
        return productEndpoint.saveProduct(token, productCreateDto);
    }

}
