package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity savePrice(String token, PriceCreateDto priceCreateDto);
}
