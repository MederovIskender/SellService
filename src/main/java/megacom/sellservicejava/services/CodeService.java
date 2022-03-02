package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;

public interface CodeService {
    void sendCode(AppUserCreationDto appUserCreationDto);
}
