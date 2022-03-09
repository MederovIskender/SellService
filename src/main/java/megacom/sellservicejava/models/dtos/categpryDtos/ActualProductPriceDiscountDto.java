package megacom.sellservicejava.models.dtos.categpryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActualProductPriceDiscountDto {
    String name;
    double price;
    double discount;
}
