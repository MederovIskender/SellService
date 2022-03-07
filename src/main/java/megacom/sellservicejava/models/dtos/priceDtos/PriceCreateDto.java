package megacom.sellservicejava.models.dtos.priceDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceCreateDto {
    String price;
    @JsonFormat(pattern = "")
    String startDate;
    String endDate;
    String productName;
}
