package megacom.sellservicejava.endpoints;

import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import org.springframework.http.ResponseEntity;

public interface AppUserEndPoint {
    ResponseEntity<?> saveAppUser(AppUserCreationDto appUserCreationDto);

    ResponseEntity<?> sendCode(String login);

    ResponseEntity<?> verifyCode(String login, String code);

    ResponseEntity<?> verifyToken(String token);
}
