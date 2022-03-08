package megacom.sellservicejava.endpoints;

import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import org.springframework.http.ResponseEntity;

public interface PriceEndpoint {
    ResponseEntity<?> savePrice(String token, PriceCreateDto priceCreateDto);
}
