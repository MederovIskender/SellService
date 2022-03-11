package megacom.sellservicejava.mappers;

import megacom.sellservicejava.models.dtos.productDtos.ProductEntityDto;
import megacom.sellservicejava.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product ProductEntityDtoToProduct(ProductEntityDto productEntityDto);
    ProductEntityDto ProductToProductEntityDto(Product Product);
}
