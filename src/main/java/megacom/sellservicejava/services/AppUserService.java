package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    ResponseEntity<?> saveAppUser(AppUserCreationDto appUserCreationDto);

    ResponseEntity<?> sendCode(String login);

    boolean AppUserLockOutChecking(AppUser appUser);

    ResponseEntity<?> getToken(String login, String code);
}
