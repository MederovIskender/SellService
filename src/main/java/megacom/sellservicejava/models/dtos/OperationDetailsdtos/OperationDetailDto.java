package megacom.sellservicejava.models.dtos.OperationDetailsdtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import megacom.sellservicejava.models.dtos.OperationDtos.OperationDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductEntityDto;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDetailDto {

    Long id;
    ProductEntityDto product;
    OperationDto operation;
    int quantity;
    double amount;
}
