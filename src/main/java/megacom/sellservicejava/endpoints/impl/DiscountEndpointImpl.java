package megacom.sellservicejava.endpoints.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.endpoints.DiscountEndpoint;
import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import megacom.sellservicejava.models.entities.Discount;
import megacom.sellservicejava.services.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DiscountEndpointImpl implements DiscountEndpoint {
    DiscountService discountService;
    AppUserEndPoint appUserEndPoint;

    public DiscountEndpointImpl(DiscountService discountService, AppUserEndPoint appUserEndPoint) {
        this.discountService = discountService;
        this.appUserEndPoint = appUserEndPoint;
    }

    @Override
    public ResponseEntity<?> saveDiscount(String token, DiscountCreateDto discountCreateDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Discount discount = discountService.saveDiscount(discountCreateDto);
        if (Objects.isNull(discount)){
            return new ResponseEntity<>("Скидка не создана", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Скидка была создана");
    }
}
