package megacom.sellservicejava.controllers;

import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {
    PriceService priceService;
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePrice(@RequestHeader String token, @RequestBody PriceCreateDto priceCreateDto){
        return priceService.savePrice(token, priceCreateDto);
    }
}
