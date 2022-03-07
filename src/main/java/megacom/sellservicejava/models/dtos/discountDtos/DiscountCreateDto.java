package megacom.sellservicejava.models.dtos.discountDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCreateDto {
    String discount;
    String startDate;
    String endDate;
    String productName;
}
