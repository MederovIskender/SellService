package megacom.sellservicejava.controllers;

import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {
    PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    public ResponseEntity<?> savePrice (@RequestHeader String token, @RequestBody PriceCreateDto priceCreateDto){
        return priceService.savePrice(token, priceCreateDto);
    }
}
