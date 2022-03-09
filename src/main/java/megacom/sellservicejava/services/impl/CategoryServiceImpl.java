package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.models.dtos.categpryDtos.ActualProductPriceDiscountDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryId;
import megacom.sellservicejava.models.entities.Category;
import megacom.sellservicejava.repos.CategoryRepo;
import megacom.sellservicejava.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
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

    @Override
    public List<ActualProductPriceDiscountDto> getActualInfoPerCategory(long categoryId) {
        return null;    }
}
