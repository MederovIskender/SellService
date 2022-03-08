package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.entities.Discount;
import megacom.sellservicejava.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepo extends JpaRepository<Discount,Long> {
    List<Discount> findAllByProductAndActiveTrue(Product product);
}
