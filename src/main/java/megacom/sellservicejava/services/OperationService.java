package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.OperationDetailsdtos.OperationDetailDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductEntityDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.models.entities.Operation;

import java.util.List;

public interface OperationService {
    ProductEntityDto findProductEntityDtoByBarcode (String barcode);
    double findActualPriceByProductId(Long id);
    double findActualDiscountByProductId(Long id);
    AppUser findUserByLogin(String login);

    void save(Operation operation);

    void saveOperationDetail(List<OperationDetailDto> operationDetailDtoList);

    Operation findOperationById(Long operationId);
}
