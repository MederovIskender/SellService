package megacom.sellservicejava.models.dtos.OperationDtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InputForOperation {
    String barcode;
    int quantity;
}
