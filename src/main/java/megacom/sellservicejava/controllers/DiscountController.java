package megacom.sellservicejava.controllers;

import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.services.DiscountService;
import megacom.sellservicejava.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/discount")
public class DiscountController {
    DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    public ResponseEntity<?> saveDiscount (@RequestHeader String token, @RequestBody DiscountCreateDto discountCreateDto){
        return discountService.saveDiscount(token, discountCreateDto);
    }
}