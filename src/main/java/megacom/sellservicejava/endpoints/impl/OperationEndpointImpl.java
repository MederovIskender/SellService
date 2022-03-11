package megacom.sellservicejava.endpoints.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.endpoints.OperationEndpoint;
import megacom.sellservicejava.mappers.OperationMapper;
import megacom.sellservicejava.models.dtos.OperationDetailsdtos.OperationDetailDto;
import megacom.sellservicejava.models.dtos.OperationDtos.InputForOperation;
import megacom.sellservicejava.models.dtos.OperationDtos.PaymentInputDto;
import megacom.sellservicejava.models.dtos.OperationDetailsdtos.ReceiptDetailsDto;
import megacom.sellservicejava.models.dtos.OperationDtos.ReceiptDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductEntityDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.models.entities.Operation;
import megacom.sellservicejava.models.responses.ErrorResponse;
import megacom.sellservicejava.services.OperationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OperationEndpointImpl implements OperationEndpoint {
    public OperationEndpointImpl(AppUserEndPoint appUserEndPoint, OperationService operationService) {
        this.appUserEndPoint = appUserEndPoint;
        this.operationService = operationService;
    }

    @Value("${jwtSecret}")
    private String secretKey;

    AppUserEndPoint appUserEndPoint;
    OperationService operationService;

    @Override
    public ResponseEntity<?> provideOperation(String token, List<InputForOperation> sellingList) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        double price;
        double discount;
        double amount;              // for OperationDetail
        double totalAmount = 0;

        List<OperationDetailDto> operationDetailDtoList = new ArrayList<>();
        List<ReceiptDetailsDto> receiptDetailsDtoList = new ArrayList<>();

        // for show to the buyer receiptDetails
        ReceiptDto receiptDto = new ReceiptDto();

        for (InputForOperation input : sellingList) {
            ProductEntityDto productEntityDto = operationService.
                    findProductEntityDtoByBarcode(input.getBarcode());
            if (Objects.isNull(productEntityDto)) {
                return new ResponseEntity<>(
                        new ErrorResponse("Некорректно введенные данный!"
                                , "Проверьте введенный штрихкод -> " + input.getBarcode())
                        , HttpStatus.NOT_FOUND);
            }
            OperationDetailDto operationDetailDto = new OperationDetailDto();
            operationDetailDto.setProduct(productEntityDto);
            operationDetailDto.setQuantity(input.getQuantity());


            price = operationService.findActualPriceByProductId(productEntityDto.getId());

            if (operationService.findActualDiscountByProductId(productEntityDto.getId()) == 0) {
                amount = price * input.getQuantity();

            } else {

                discount = 1 - (operationService.findActualDiscountByProductId(productEntityDto.getId()) / 100);
                amount = (price * discount) * input.getQuantity();
            }

            totalAmount += amount;

            operationDetailDto.setAmount(amount);
            operationDetailDtoList.add(operationDetailDto);

            ReceiptDetailsDto receiptDetailsDto = new ReceiptDetailsDto();
            receiptDetailsDto.setName(productEntityDto.getProductName());
            receiptDetailsDto.setBarcode(productEntityDto.getBarcode());
            receiptDetailsDto.setQuantity(input.getQuantity());
            receiptDetailsDto.setPrice(price);
            receiptDetailsDto.setDiscount(operationService.findActualDiscountByProductId(productEntityDto.getId()));
            receiptDetailsDto.setAmount(amount);

            receiptDetailsDtoList.add(receiptDetailsDto);
        }
        Jws<Claims> jwt =
                Jwts
                        .parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token);

        AppUser appUser = operationService.findUserByLogin((String)jwt.getBody().get("login"));
        receiptDto.setCashier(appUser.getName());
        receiptDto.setTotalAmount(totalAmount);
        receiptDto.setReceiptDetailsDto(receiptDetailsDtoList);

        Operation operation = new Operation();
        operation.setTotalAmount(totalAmount);
        operation.setAppUser(appUser);
        operationService.save(operation);

        for (OperationDetailDto element : operationDetailDtoList) {
            element.setOperation(OperationMapper
                                    .INSTANCE
                                    .mapToOperationDto(operation));
        }
        operationService.saveOperationDetail(operationDetailDtoList);
        return ResponseEntity.ok(receiptDto);
    }

    @Override
    public ResponseEntity<?> payment(String token, PaymentInputDto paymentInputDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Operation operation = operationService.findOperationById(paymentInputDto.getOperationId());

        if (Objects.isNull(operation)) {
            return new ResponseEntity<>(
                    new ErrorResponse("Не найдена операция!"
                            , "Вы ввели некорректный ID операции!")
                    , HttpStatus.NOT_FOUND);
        }

        double change = paymentInputDto.getCash() - operation.getTotalAmount();

        if (change < 0) {
            return new ResponseEntity<>(
                    new ErrorResponse("Недостаточно средств для проведения операции!", null)
                    , HttpStatus.CONFLICT);
        }

        operation.setCash(paymentInputDto.getCash());
        operation.setChange(change);

        operationService.save(operation);

        return ResponseEntity.ok(OperationMapper
                        .INSTANCE
                        .mapToOperationDto(operation)
                );}
    }
