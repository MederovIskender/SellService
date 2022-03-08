package megacom.sellservicejava.controllers;

import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    ProductService productService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestHeader String token, @RequestBody ProductCreateDto productCreateDto){
        return productService.saveProduct(token, productCreateDto);
    }

}
