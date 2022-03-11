package megacom.sellservicejava.controllers;

import megacom.sellservicejava.endpoints.PriceEndpoint;
import megacom.sellservicejava.models.dtos.categpryDtos.CategoryCreateDto;
import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    public PriceController(PriceEndpoint priceEndpoint) {
        this.priceEndpoint = priceEndpoint;
    }

    PriceEndpoint priceEndpoint;

    @PostMapping("/save")
    public ResponseEntity<?> savePrice(@RequestHeader String token, @RequestBody PriceCreateDto priceCreateDto){
        return priceEndpoint.savePrice(token, priceCreateDto);
    }
}
