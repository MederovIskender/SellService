package megacom.sellservicejava.services.impl;

import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.repos.AppUserRepo;
import megacom.sellservicejava.services.AppUserService;

@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    AppUserRepo appUserRepo;

    @Override
    public AppUser save(AppUserCreationDto appUserCreationDto) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserCreationDto.getName());
        appUser.setLogin(appUserCreationDto.getLogin());
        appUser = appUserRepo.save(appUser);
        return appUser;

    }
}

