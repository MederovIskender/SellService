package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.models.entities.Price;
import megacom.sellservicejava.repos.PriceRepo;
import megacom.sellservicejava.services.PriceService;
import megacom.sellservicejava.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
    AppUserEndPoint appUserEndPoint;
    PriceRepo priceRepo;
    ProductService productService;

    public PriceServiceImpl(AppUserEndPoint appUserEndPoint, PriceRepo priceRepo, ProductService productService) {
        this.appUserEndPoint = appUserEndPoint;
        this.priceRepo = priceRepo;
        this.productService = productService;
    }

    @Override
    public ResponseEntity savePrice(String token, PriceCreateDto priceCreateDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Price price = new Price();
        price.setPrice(priceCreateDto.getPrice());
        price.setActive(true);
        price.setStartDate(LocalDateTime.parse(priceCreateDto.getStartDate()));
        price.setEndDate(LocalDateTime.parse(priceCreateDto.getEndDate()));
        price.setProduct(productService.findProductByName(priceCreateDto.getProductName()));
        List<Price> listOfPrices = priceRepo.findAllByProductAndActiveTrue(price.getProduct());
        for (Price p:listOfPrices){
            if ((p.getStartDate().isAfter(price.getStartDate())&&
                    p.getStartDate().isBefore(price.getEndDate()))||
                    (p.getEndDate().isAfter(price.getStartDate())&&
                            p.getEndDate().isBefore(price.getEndDate()))
            ) { p.setActive(false);
            }
        }
        priceRepo.save(price);
        return ResponseEntity.ok("Цена на "+ priceCreateDto.getProductName()+ " была успешно установлена");
    }
}
