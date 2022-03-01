package megacom.sellservicejava.models.dtos.discountDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import megacom.sellservicejava.models.entities.Product;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountEntityDto {
    long id;
    String discount;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Product product;
}
