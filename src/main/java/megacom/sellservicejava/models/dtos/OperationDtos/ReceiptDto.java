package megacom.sellservicejava.models.dtos.OperationDtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import megacom.sellservicejava.models.dtos.OperationDetailsdtos.ReceiptDetailsDto;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReceiptDto {

    List<ReceiptDetailsDto> receiptDetailsDto;
    double totalAmount;
    String cashier;
}