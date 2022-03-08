package megacom.sellservicejava.services.impl;


import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.repos.AppUserRepo;
import megacom.sellservicejava.services.AppUserService;
import megacom.sellservicejava.services.CodeService;
import megacom.sellservicejava.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Service
public class AppUserServiceImpl implements AppUserService {
    AppUserRepo appUserRepo;

    public AppUserServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public ResponseEntity<?> saveAppUser(AppUserCreationDto appUserCreationDto) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserCreationDto.getName());
        appUser.setLogin(appUserCreationDto.getLogin());
        appUser.setActive(true);
        if(Objects.isNull(appUserRepo.findByLogin(appUser.getLogin()))){
           appUser = appUserRepo.save(appUser);
        } else {
            return new ResponseEntity<>("Такой пользователь уже сущесвует", HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(appUser);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        return appUserRepo.save(appUser);
    }

    @Override
    public boolean AppUserLockOutChecking(AppUser appUser) {
        if(Objects.nonNull(appUser.getBlockEndDate())){
            if(LocalDateTime.now().isBefore(appUser.getBlockEndDate())){
                return true;
            }
        } return false;
    }

    @Override
    public AppUser findUserByLogin(String login) {
        return appUserRepo.findByLogin(login);
    }


    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

}

