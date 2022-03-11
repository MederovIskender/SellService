package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.discountDtos.DiscountCreateDto;
import megacom.sellservicejava.models.entities.Discount;

public interface DiscountService {
    Discount saveDiscount(DiscountCreateDto discountCreateDto);

    double findActualDiscount(Long productId);
}
