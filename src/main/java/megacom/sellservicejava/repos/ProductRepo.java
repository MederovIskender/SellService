package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByProductName(String productName);
    Product findByBarcode(String barcode);
}
