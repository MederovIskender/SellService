package megacom.sellservicejava.endpoints;

import megacom.sellservicejava.models.dtos.OperationDtos.InputForOperation;
import megacom.sellservicejava.models.dtos.OperationDtos.PaymentInputDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationEndpoint {
    ResponseEntity<?> provideOperation(String token, List<InputForOperation> sellingList);

    ResponseEntity<?> payment(String token, PaymentInputDto paymentInputDto);

}
