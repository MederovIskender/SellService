package megacom.sellservicejava.services.impl;

import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.mappers.AppUserMapper;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.repos.AppUserRepo;
import megacom.sellservicejava.services.AppUserService;
import megacom.sellservicejava.services.CodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {
    AppUserRepo appUserRepo;
    CodeService codeService;

    @Override
    public ResponseEntity<?> saveAppUser(AppUserCreationDto appUserCreationDto) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserCreationDto.getName());
        appUser.setLogin(appUserCreationDto.getLogin());
        if(Objects.isNull(appUserRepo.findByLogin(appUser.getLogin()))){
            appUserRepo.save(appUser);
        } else {
            return new ResponseEntity<>("Такой пользователь уже сущесвует", HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(appUser);
    }

    @Override
    public ResponseEntity<?> sendCode(String login) {
        AppUser appUser = appUserRepo.findByLogin(login);
        if(Objects.isNull(appUser)){
            return new ResponseEntity<>("Неверный логин", HttpStatus.NOT_FOUND);
        }
        boolean check = AppUserLockOutChecking(appUser);
        if (check){
            return new ResponseEntity<>("Превышено количество попыток входа, вы заблокированы. Повторите попытку", HttpStatus.CONFLICT);
        }
        codeService.sendCode(AppUserMapper.INSTANCE.AppUserToAppUserCreateDto(appUser));
    return null;
    }

    @Override
    public boolean AppUserLockOutChecking(AppUser appUser) {
        if(Objects.nonNull(appUser.getBlockDate())){
            if(LocalDateTime.now().isBefore(appUser.getBlockDate())){
                return true;
            }
        }
        return false;
    }
}

