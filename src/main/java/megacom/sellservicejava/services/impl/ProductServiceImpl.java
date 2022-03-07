package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.models.entities.Category;
import megacom.sellservicejava.models.entities.Product;
import megacom.sellservicejava.repos.ProductRepo;
import megacom.sellservicejava.services.CategoryService;
import megacom.sellservicejava.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    public ProductServiceImpl(AppUserEndPoint appUserEndPoint, ProductRepo productRepo, CategoryService categoryService) {
        this.appUserEndPoint = appUserEndPoint;
        this.productRepo = productRepo;
        this.categoryService = categoryService;
    }

    AppUserEndPoint appUserEndPoint;
    ProductRepo productRepo;
    CategoryService categoryService;

    @Override
    public ResponseEntity<?> saveProduct(String token, ProductCreateDto productCreateDto) {

        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Product product = new Product();
        product.setProductName(productCreateDto.getProductName());
        product.setActive(true);
        product.setCategory(categoryService.findCategoryByCategoryName(productCreateDto.getCategoryName()));
        product.setBarcode(generateBarcode());
        while(Objects.nonNull(productRepo.findByBarcode(product.getBarcode()))) {
            product.setBarcode(generateBarcode());
        }
        if(Objects.isNull(productRepo.findByProductName(productCreateDto.getProductName()))){
            productRepo.save(product);
        } else {
            return new ResponseEntity<>("Такой продукт уже существует", HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Продукт "+product.getProductName()+" был успешно сохранен");
    }

    String generateBarcode(){
        Random random = new Random();
        char[] digits = new char[12];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 12; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
            }
            return (new String(digits));
    }
}
