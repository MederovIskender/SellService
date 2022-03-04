package megacom.sellservicejava.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/AppUser")
public class AppUserController {

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    private AppUserService appUserService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody AppUserCreationDto appUserCreationDto){
        return appUserService.saveAppUser(appUserCreationDto);
    }
    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@RequestParam String login){
        return appUserService.sendCode(login);
    }

    @GetMapping("/token")
    public ResponseEntity<?> getToken(@RequestParam String login, String code){
        return appUserService.getToken(login,code);
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader String token){
        return appUserService.verifyToken(token);
    }
}
