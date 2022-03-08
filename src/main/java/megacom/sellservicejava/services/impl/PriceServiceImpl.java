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
    PriceRepo priceRepo;
    ProductService productService;

    public PriceServiceImpl(PriceRepo priceRepo, ProductService productService) {
        this.priceRepo = priceRepo;
        this.productService = productService;
    }

    @Override
    public Price savePrice(PriceCreateDto priceCreateDto) {
        Price price = new Price();
        price.setPrice(priceCreateDto.getPrice());
        price.setActive(true);
        price.setStartDate(priceCreateDto.getStartDate());
        price.setEndDate(priceCreateDto.getEndDate());
        price.setProduct(productService.findProductById(priceCreateDto.getProductId()));
        List<Price> listOfPrices = priceRepo.findAllByProductAndActiveTrue(price.getProduct());
        for (Price p:listOfPrices){
            if(p.getStartDate().equals(price.getStartDate())||p.getEndDate().equals(price.getEndDate())){
                p.setActive(false);
                priceRepo.save(p);
            } else if ((p.getStartDate().isAfter(price.getStartDate())&&
                    p.getStartDate().isBefore(price.getEndDate()))||
                    (p.getEndDate().isAfter(price.getStartDate())&&
                            p.getEndDate().isBefore(price.getEndDate()))
            ) { p.setActive(false);
                priceRepo.save(p);
            }
        }
        price = priceRepo.save(price);
        return price;
    }
}
