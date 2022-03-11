package megacom.sellservicejava.endpoints.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
    public DiscountEndpointImpl(DiscountService discountService, AppUserEndPoint appUserEndPoint) {
        this.discountService = discountService;
        this.appUserEndPoint = appUserEndPoint;
    }

    DiscountService discountService;
    AppUserEndPoint appUserEndPoint;


    @Override
    public ResponseEntity<?> saveDiscount(String token, DiscountCreateDto discountCreateDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Discount discount = discountService.saveDiscount(discountCreateDto);
        if (discount.getDiscount()>=100){
            return new ResponseEntity<>("Скидка не может быть больше 100%", HttpStatus.CONFLICT);
        }
        if (Objects.isNull(discount)){
            return new ResponseEntity<>("Скидка не создана", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Скидка была создана");
    }
}
