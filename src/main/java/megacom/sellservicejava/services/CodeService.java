package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.AppCodeDtos.AppCodeEntityDto;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppCode;

public interface CodeService {
    void saveCode(AppCodeEntityDto appCodeEntityDto);
    void sendCode(AppUserCreationDto appUserCreationDto);
    AppCode findLastCode(AppUserCreationDto appUserCreationDto);
}
