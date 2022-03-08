package megacom.sellservicejava.endpoints;

import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import org.springframework.http.ResponseEntity;

public interface DiscountEndpoint {
    ResponseEntity<?> saveDiscount(String token, DiscountCreateDto discountCreateDto);
}
