package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.dtos.categpryDtos.ActualProductPriceDiscountDto;
import megacom.sellservicejava.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Product findByBarcode(String barcode);
    Product findProductById(long id);

    @Query (value = "select * from products where categories_id=?1", nativeQuery = true)
    List<Product> findAllProductsByCategory(long categoryId);
}
