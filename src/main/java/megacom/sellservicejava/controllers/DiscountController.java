package megacom.sellservicejava.controllers;

import megacom.sellservicejava.endpoints.DiscountEndpoint;
import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.services.DiscountService;
import megacom.sellservicejava.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/discount")
public class DiscountController {
    DiscountEndpoint discountEndpoint;

    public DiscountController(DiscountEndpoint discountEndpoint) {
        this.discountEndpoint = discountEndpoint;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDiscount(@RequestHeader String token, @RequestBody DiscountCreateDto discountCreateDto){
        return discountEndpoint.saveDiscount(token, discountCreateDto);
    }
}