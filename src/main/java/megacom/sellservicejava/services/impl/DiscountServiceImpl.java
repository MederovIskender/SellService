package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import megacom.sellservicejava.models.entities.Discount;
import megacom.sellservicejava.models.entities.Price;
import megacom.sellservicejava.repos.DiscountRepo;
import megacom.sellservicejava.services.DiscountService;
import megacom.sellservicejava.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    ProductService productService;
    DiscountRepo discountRepo;

    public DiscountServiceImpl(ProductService productService, DiscountRepo discountRepo) {
        this.productService = productService;
        this.discountRepo = discountRepo;
    }

    @Override
    public Discount saveDiscount(DiscountCreateDto discountCreateDto) {
        Discount discount = new Discount();
        discount.setDiscount(discountCreateDto.getDiscount());
        discount.setActive(true);
        discount.setStartDate(discountCreateDto.getStartDate());
        discount.setEndDate(discountCreateDto.getEndDate());
        discount.setProduct(productService.findProductById(discountCreateDto.getProductId()));
        List<Discount> listOfDiscounts = discountRepo.findAllByProductAndActiveTrue(discount.getProduct());
        for (Discount p:listOfDiscounts){
            if(p.getStartDate().equals(discount.getStartDate())||p.getEndDate().equals(discount.getEndDate())){
                p.setActive(false);
                discountRepo.save(p);
            } else if ((p.getStartDate().isAfter(discount.getStartDate())&&
                    p.getStartDate().isBefore(discount.getEndDate()))||
                    (p.getEndDate().isAfter(discount.getStartDate())&&
                            p.getEndDate().isBefore(discount.getEndDate()))
            ) { p.setActive(false);
                discountRepo.save(p);
            }
        }
        discount = discountRepo.save(discount);
        return discount;
    }
}
