package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.dtos.categpryDtos.ActualProductPriceDiscountDto;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryId;
import megacom.sellservicejava.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByName(String categoryName);

    @Query("Select p.name,d.discount,p2.price from products p")
    List<ActualProductPriceDiscountDto>findActualInfoByCategoryId(long categoryId);
}
