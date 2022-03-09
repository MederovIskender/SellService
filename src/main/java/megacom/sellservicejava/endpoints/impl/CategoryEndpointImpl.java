package megacom.sellservicejava.endpoints.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.endpoints.CategoryEndpoint;
import megacom.sellservicejava.models.dtos.categpryDtos.ActualProductPriceDiscountDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryId;
import megacom.sellservicejava.models.entities.Category;
import megacom.sellservicejava.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryEndpointImpl implements CategoryEndpoint {

    CategoryService categoryService;
    AppUserEndPoint appUserEndPoint;

    public CategoryEndpointImpl(CategoryService categoryService, AppUserEndPoint appUserEndPoint) {
        this.categoryService = categoryService;
        this.appUserEndPoint = appUserEndPoint;
    }

    @Override
    public ResponseEntity<?> saveCategory(String token, CategoryCreateDto categoryCreateDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Category category = categoryService.saveCategory(token, categoryCreateDto);
        if (Objects.isNull(category)){
            return new ResponseEntity<>("такая категрия уже есть", HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Категория "+category+" была успешно создана");
    }

    @Override
    public ResponseEntity<?> getActualInfo(String token, long categoryId) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<ActualProductPriceDiscountDto>actualInfo = categoryService.getActualInfoPerCategory(categoryId);
        return ResponseEntity.ok(actualInfo);
    }
}
