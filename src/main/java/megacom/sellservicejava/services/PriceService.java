package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.models.entities.Price;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    Price savePrice(PriceCreateDto priceCreateDto);
}
