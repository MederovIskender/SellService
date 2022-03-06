package megacom.sellservicejava.services;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;

public interface AppUserService {
    ResponseEntity<?> saveAppUser(AppUserCreationDto appUserCreationDto);

    AppUser saveUser(AppUser appUser);

    boolean AppUserLockOutChecking(AppUser appUser);

    AppUser findUserByLogin(String login);

    Date convertToDateViaInstant(LocalDateTime dateToConvert);

}
