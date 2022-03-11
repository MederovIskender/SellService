package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.entities.Discount;
import megacom.sellservicejava.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepo extends JpaRepository<Discount,Long> {
    List<Discount> findAllByProductAndActiveTrue(Product product);

    @Query(value = "select discount from discounts where id_product = ?1 and active = true and current_timestamp > start_date and current_timestamp < end_date"
            ,nativeQuery = true)
    double findActualDiscount(Long productId);
}
