package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;

public interface AppUserService {
    AppUser save(AppUserCreationDto appUserCreationDto);
}
