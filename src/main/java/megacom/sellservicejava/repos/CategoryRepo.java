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

    @Query(value ="Select p.name,d.discount,p2.price from products p" +
            "innerjoin discounts d on p.id=d.id_Product" +
            "innerjoin prices p2 on p.id=id_Product where d.active = true and p2.active =true and p.categories_id=?1", nativeQuery = true)
    List<ActualProductPriceDiscountDto>findActualInfoByCategoryId(long categoryId);
}
