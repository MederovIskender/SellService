package megacom.sellservicejava.mappers;

import megacom.sellservicejava.models.dtos.OperationDetailsdtos.OperationDetailDto;
import megacom.sellservicejava.models.entities.OperationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationDetailMapper {

    OperationDetailMapper INSTANCE = Mappers.getMapper(OperationDetailMapper.class);

    OperationDetail mapToOperationDetail(OperationDetailDto operationDetailDto);

    OperationDetailDto mapToOperationDetailDto(OperationDetail operationDetail);
}
