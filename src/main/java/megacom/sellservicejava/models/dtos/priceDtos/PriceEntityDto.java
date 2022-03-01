package megacom.sellservicejava.models.dtos.priceDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import megacom.sellservicejava.models.entities.Product;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntityDto {
    long id;
    String price;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Product product;
}
