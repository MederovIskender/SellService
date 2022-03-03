package megacom.sellservicejava.mappers;

import megacom.sellservicejava.models.dtos.AppCodeDtos.AppCodeEntityDto;
import megacom.sellservicejava.models.entities.AppCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppCodeMapper {
    AppCodeMapper INSTANCE = Mappers.getMapper(AppCodeMapper.class);
    AppCode AppCodeEntityDtoToAppCode(AppCodeEntityDto appCodeEntityDto);
    AppCodeEntityDto AppCodeToAppCodeEntityDto(AppCode appCode);
}
