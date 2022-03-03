package megacom.sellservicejava.services.impl;

import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.enums.CodeStatus;
import megacom.sellservicejava.mappers.AppCodeMapper;
import megacom.sellservicejava.mappers.AppUserMapper;
import megacom.sellservicejava.models.dtos.AppCodeDtos.AppCodeEntityDto;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.repos.AppUserRepo;
import megacom.sellservicejava.services.AppUserService;
import megacom.sellservicejava.services.CodeService;
import megacom.sellservicejava.services.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {
    AppUserRepo appUserRepo;
    CodeService codeService;
    RequestService requestService;

    @Value("${jwtSecret}")
    private String secretKey;

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
            DateTimeFormatter formatToShowEndDate = DateTimeFormatter.ofPattern("mm:ss");
            LocalDateTime remainingBlockTime = LocalDateTime.now().until(appUser.getBlockEndDate(), ChronoUnit.MINUTES)
            return new ResponseEntity<>("Превышено количество попыток входа, вы заблокированы. Повторите попытку через " + formatToShowEndDate.format(remainingBlockTime),
                    HttpStatus.CONFLICT);
            appUser.getBlockEndDate().format(formatToShowEndDate)
        }
        codeService.sendCode(AppUserMapper.INSTANCE.AppUserToAppUserCreateDto(appUser));
        return ResponseEntity.ok("код успешно отправлен");
    }

    @Override
    public boolean AppUserLockOutChecking(AppUser appUser) {
        if(Objects.nonNull(appUser.getBlockEndDate())){
            if(LocalDateTime.now().isBefore(appUser.getBlockEndDate())){
                return true;
            }
        }
        return false;
    }

    @Override
    public ResponseEntity<?> getToken(String login, String code) {
        AppUser appUser = appUserRepo.findByLogin(login);
        if(Objects.isNull(appUser)){
            return new ResponseEntity<>("Неверный логин", HttpStatus.NOT_FOUND);
        }
        boolean check = AppUserLockOutChecking(appUser);
        if (check){
            return new ResponseEntity<>("Превышено количество попыток входа, вы заблокированы. Повторите попытку", HttpStatus.CONFLICT);
        }
        AppCodeEntityDto checkUserCode = AppCodeMapper.INSTANCE.AppCodeToAppCodeEntityDto(
                codeService.findLastCode(AppUserMapper.INSTANCE.AppUserToAppUserCreateDto(appUser)));

        if(LocalDateTime.now().isAfter(checkUserCode.getEndDate())) {
            return new ResponseEntity<>("Время действия кода подтвеждения истекло, вам нужно получить код подтвеждения повторно", HttpStatus.CONFLICT);
        }
        if (!BCrypt.checkpw(code,checkUserCode.getCode())){
            requestService.saveRequest(checkUserCode, false);
            if(requestService.countFailedAttempts(checkUserCode)>=3){
                appUser.setBlockEndDate(LocalDateTime.now().plusMinutes(3);
                appUserRepo.save(appUser);
                checkUserCode.setCodeStatus(CodeStatus.FAILED);
                codeService.saveCode(checkUserCode);
            }
            return new ResponseEntity<>("Авторизация не пройдена! Вы ввели некоректный код подтвеждения", HttpStatus.NOT_FOUND);
        }
        requestService.saveRequest(checkUserCode, true);
        LocalDateTime tokensTimeLife = LocalDateTime.now().plusHours(3);
        String token =
                Jwts.builder()
                        .claim("login e-mail", login)
                        .setExpiration(tokensTimeLife)
                        .signWith(
                                SignatureAlgorithm.HS256
                                , secretKey)
                        .compact();


    }
}

