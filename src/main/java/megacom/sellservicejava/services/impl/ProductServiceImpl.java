package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.mappers.ProductMapper;
import megacom.sellservicejava.models.dtos.categpryDtos.ActualProductPriceDiscountDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductCreateDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductEntityDto;
import megacom.sellservicejava.models.entities.Product;
import megacom.sellservicejava.repos.ProductRepo;
import megacom.sellservicejava.services.CategoryService;
import megacom.sellservicejava.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    public ProductServiceImpl(ProductRepo productRepo, CategoryService categoryService) {
        this.productRepo = productRepo;
        this.categoryService = categoryService;
    }
    ProductRepo productRepo;
    CategoryService categoryService;

    @Override
    public Product saveProduct(ProductCreateDto productCreateDto) {
        Product product = new Product();
        product.setName(productCreateDto.getProductName());
        product.setActive(true);
        product.setCategory(categoryService.findCategoryByCategoryName(productCreateDto.getCategoryName()));
        product.setBarcode(generateBarcode());
        while(Objects.nonNull(productRepo.findByBarcode(product.getBarcode()))) {
            product.setBarcode(generateBarcode());
        }
        if(Objects.isNull(findProductByName(productCreateDto.getProductName()))){
            productRepo.save(product);
            return product;
        } else {
            return null;
        }
    }

    @Override
    public Product findProductByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public Product findProductById(long id) {
        return productRepo.findProductById(id);
    }

    @Override
    public List<Product> findAllProductsByCategory(long categoryId) {
        return productRepo.findAllProductsByCategory(categoryId);
    }

    @Override
    public Product findProductByBarcode(String barcode) {
        return productRepo.findByBarcode(barcode);
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
