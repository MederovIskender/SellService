package megacom.sellservicejava.models.dtos.OperationDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInputDto {
    Long operationId;
    double cash;
}
