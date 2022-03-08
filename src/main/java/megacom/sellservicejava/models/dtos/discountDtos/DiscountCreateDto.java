package megacom.sellservicejava.models.dtos.discountDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCreateDto {
    double discount;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long productId;
}
