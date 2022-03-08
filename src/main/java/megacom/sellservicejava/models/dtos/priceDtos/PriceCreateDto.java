package megacom.sellservicejava.models.dtos.priceDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceCreateDto {
    double price;
    LocalDateTime startDate;
    LocalDateTime endDate;
    long productId;
}
