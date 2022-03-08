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
    public Category saveCategory(String token, CategoryCreateDto categoryCreateDto) {
        Category newCategory = new Category();
        newCategory.setName(categoryCreateDto.getCategoryName());
        newCategory.setActive(true);
        if(Objects.isNull(findCategoryByCategoryName(newCategory.getName()))){
            categoryRepo.save(newCategory);
            return newCategory;
        } else {
            return null;
        }
    }
    public Category findCategoryByCategoryName(String name){
        return categoryRepo.findByName(name);
    }
}
