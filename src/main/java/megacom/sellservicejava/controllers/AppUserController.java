package megacom.sellservicejava.controllers;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/AppUser")
public class AppUserController {
    private final AppUserEndPoint appUserEndPoint;

    public AppUserController(AppUserEndPoint appUserEndPoint) {
        this.appUserEndPoint = appUserEndPoint;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody AppUserCreationDto appUserCreationDto){
        return appUserEndPoint.saveAppUser(appUserCreationDto);
    }
    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@RequestHeader String login){
        return appUserEndPoint.sendCode(login);
    }

    @GetMapping("/token")
    public ResponseEntity<?> verifyCode(@RequestParam String login, String code){
        return appUserEndPoint.verifyCode(login,code);
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader String token){
        return appUserEndPoint.verifyToken(token);
    }
}
