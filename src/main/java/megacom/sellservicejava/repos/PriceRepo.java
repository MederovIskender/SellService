package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.entities.Price;
import megacom.sellservicejava.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price,Long> {
    List<Price>findAllByProductAndActiveTrue(Product product);
}
