package megacom.sellservicejava.controllers;

import megacom.sellservicejava.endpoints.OperationEndpoint;
import megacom.sellservicejava.models.dtos.OperationDtos.InputForOperation;
import megacom.sellservicejava.models.dtos.OperationDtos.PaymentInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/operation")
public class OperationController {

    @Autowired
    private OperationEndpoint operationEndpoint;

    @PostMapping("/provideOperation")
    public ResponseEntity<?> provideOperation(@RequestHeader String token, @RequestBody List<InputForOperation> sellingList ) {
        return operationEndpoint.provideOperation(token, sellingList);
    }

    @GetMapping("/payment")
    public ResponseEntity<?> payment(@RequestHeader String token, @RequestParam Long operationId, @RequestParam double cash) {
        return operationEndpoint.payment(token, operationId, cash);
    }
}
