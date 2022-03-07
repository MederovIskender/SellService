package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import megacom.sellservicejava.models.entities.Discount;
import megacom.sellservicejava.repos.DiscountRepo;
import megacom.sellservicejava.services.DiscountService;
import megacom.sellservicejava.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiscountServiceImpl implements DiscountService {

    AppUserEndPoint appUserEndPoint;
    ProductService productService;
    DiscountRepo discountRepo;

    public DiscountServiceImpl(AppUserEndPoint appUserEndPoint, ProductService productService, DiscountRepo discountRepo) {
        this.appUserEndPoint = appUserEndPoint;
        this.productService = productService;
        this.discountRepo = discountRepo;
    }

    @Override
    public ResponseEntity<?> saveDiscount(String token, DiscountCreateDto discountCreateDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Discount discount = new Discount();
        discount.setDiscount(discountCreateDto.getDiscount());
        discount.setActive(true);
        discount.setStartDate(LocalDateTime.parse(discountCreateDto.getStartDate()));
        discount.setEndDate(LocalDateTime.parse(discountCreateDto.getEndDate()));

        return null;
    }
}
