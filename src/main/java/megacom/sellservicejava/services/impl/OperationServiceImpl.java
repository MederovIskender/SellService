package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.mappers.OperationDetailMapper;
import megacom.sellservicejava.mappers.OperationMapper;
import megacom.sellservicejava.mappers.ProductMapper;
import megacom.sellservicejava.models.dtos.OperationDetailsdtos.OperationDetailDto;
import megacom.sellservicejava.models.dtos.productDtos.ProductEntityDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.models.entities.Operation;
import megacom.sellservicejava.models.entities.OperationDetail;
import megacom.sellservicejava.repos.OperationRepo;
import megacom.sellservicejava.services.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    ProductService productService;
    PriceService priceService;
    DiscountService discountService;
    OperationRepo operationRepo;
    AppUserService appUserService;
    OperationDetailService operationDetailService;

    public OperationServiceImpl(ProductService productService,
                                PriceService priceService,
                                DiscountService discountService,
                                OperationRepo operationRepo,
                                AppUserService appUserService,
                                OperationDetailService operationDetailService) {
        this.productService = productService;
        this.priceService = priceService;
        this.discountService = discountService;
        this.operationRepo = operationRepo;
        this.appUserService = appUserService;
        this.operationDetailService = operationDetailService;
    }

    @Override
    public ProductEntityDto findProductEntityDtoByBarcode(String barcode) {
        return ProductMapper.
                INSTANCE.
                ProductToProductEntityDto(productService.
                        findProductByBarcode(barcode));
    }

    @Override
    public double findActualPriceByProductId(Long id) {
        return priceService.findActualPrice(id);
    }

    @Override
    public double findActualDiscountByProductId(Long id) {
        return discountService.findActualDiscount(id);
    }

    @Override
    public AppUser findUserByLogin(String login) {
        return appUserService.findUserByLogin(login);
    }

    @Override
    public void save(Operation operation) {
        operationRepo.save(operation);
    }

    @Override
    public void saveOperationDetail(List<OperationDetailDto> operationDetailDtoList) {
        for (OperationDetailDto element: operationDetailDtoList) {
            OperationDetail operationDetail = new OperationDetail();
            operationDetail.setId(element.getId());
            operationDetail.setQuantity(element.getQuantity());
            operationDetail.setProduct(ProductMapper.INSTANCE.ProductEntityDtoToProduct(element.getProduct()));
            operationDetail.setAmount(element.getAmount());
            operationDetail.setOperation(OperationMapper.INSTANCE.mapToOperation(element.getOperation()));
            operationDetailService.saveOperationDetail(operationDetail);
        }
    }

    @Override
    public Operation findOperationById(Long operationId) {
        return operationRepo.findOperationById(operationId);
    }
}
