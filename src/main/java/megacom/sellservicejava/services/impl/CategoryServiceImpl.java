package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.entities.Category;
import megacom.sellservicejava.repos.CategoryRepo;
import megacom.sellservicejava.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepo categoryRepo;
    AppUserEndPoint appUserEndPoint;

    public CategoryServiceImpl(CategoryRepo categoryRepo, AppUserEndPoint appUserEndPoint) {
        this.categoryRepo = categoryRepo;
        this.appUserEndPoint = appUserEndPoint;
    }

    @Override
    public ResponseEntity<?> saveCategory(String token, CategoryCreateDto categoryCreateDto) {

        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryCreateDto.getCategoryName());
        newCategory.setActive(true);
        if(Objects.isNull(findCategoryByCategoryName(newCategory.getCategoryName())))
        categoryRepo.save(newCategory);
        return ResponseEntity.ok("Категория "+newCategory+" была успешно создана");
    }
    public Category findCategoryByCategoryName(String name){
        return categoryRepo.findByCategoryName(name);
    }
}
