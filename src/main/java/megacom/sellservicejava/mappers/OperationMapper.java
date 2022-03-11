package megacom.sellservicejava.mappers;

import megacom.sellservicejava.models.dtos.OperationDtos.OperationDto;
import megacom.sellservicejava.models.entities.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    Operation mapToOperation(OperationDto operationDto);

    OperationDto mapToOperationDto(Operation operation);
}
