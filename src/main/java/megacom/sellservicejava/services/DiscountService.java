package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    ResponseEntity<?> saveDiscount(String token, DiscountCreateDto discountCreateDto);
}
