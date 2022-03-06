package megacom.sellservicejava.endpoints.impl;

import io.jsonwebtoken.*;
import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.enums.CodeStatus;
import megacom.sellservicejava.mappers.AppCodeMapper;
import megacom.sellservicejava.mappers.AppUserMapper;
import megacom.sellservicejava.models.dtos.AppCodeDtos.AppCodeEntityDto;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppUser;
import megacom.sellservicejava.services.AppUserService;
import megacom.sellservicejava.services.CodeService;
import megacom.sellservicejava.services.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
public class AppUserEndPointImpl implements AppUserEndPoint {
    AppUserService appUserService;
    CodeService codeService;
    RequestService requestService;

    @Value("${jwtSecret}")
    private String secretKey;

    public AppUserEndPointImpl(AppUserService appUserService, CodeService codeService, RequestService requestService) {
        this.appUserService = appUserService;
        this.codeService = codeService;
        this.requestService = requestService;
    }

    @Override
    public ResponseEntity<?> saveAppUser(AppUserCreationDto appUserCreationDto) {
        return appUserService.saveAppUser(appUserCreationDto);
    }

    @Override
    public ResponseEntity<?> sendCode(String login) {
        AppUser appUser = appUserService.findUserByLogin(login);
        if(Objects.isNull(appUser)){
            return new ResponseEntity<>("Неверный логин", HttpStatus.NOT_FOUND);
        }
        boolean check = appUserService.AppUserLockOutChecking(appUser);
        if (check){
            long remainingBlockTime = LocalDateTime.now().until(appUser.getBlockEndDate(), ChronoUnit.MILLIS);
            Duration dur = Duration.ofMillis(remainingBlockTime);
            long mm = dur.toMinutes();
            long ss = dur.toSeconds();
            String remTime = String.format("%02d:%02d", mm,ss);
            return new ResponseEntity<>("Превышено количество попыток входа, вы заблокированы. Повторите попытку через " + remTime,
                    HttpStatus.CONFLICT);
        }
        codeService.sendCode(AppUserMapper.INSTANCE.AppUserToAppUserCreateDto(appUser));
        return ResponseEntity.ok("код успешно отправлен");
    }
    @Override
    public ResponseEntity<?> getToken(String login, String code) {
        AppUser appUser = appUserService.findUserByLogin(login);
        if(Objects.isNull(appUser)){
            return new ResponseEntity<>("Неверный логин", HttpStatus.NOT_FOUND);
        }
        boolean check = appUserService.AppUserLockOutChecking(appUser);
        if (check){
            return new ResponseEntity<>("Превышено количество попыток входа, вы заблокированы. Повторите попытку позже", HttpStatus.CONFLICT);
        }
        AppCodeEntityDto checkUserCode = AppCodeMapper.INSTANCE.AppCodeToAppCodeEntityDto(
                codeService.findLastCode(AppUserMapper.INSTANCE.AppUserToAppUserCreateDto(appUser)));

        if(LocalDateTime.now().isAfter(checkUserCode.getEndDate())) {
            return new ResponseEntity<>("Время действия кода подтвеждения истекло, вам нужно получить код подтвеждения повторно", HttpStatus.CONFLICT);
        }
        if (!BCrypt.checkpw(code,checkUserCode.getCode())){
            requestService.saveRequest(checkUserCode, false);
            if(requestService.countFailedAttempts(checkUserCode)>=3){
                appUser.setBlockEndDate(LocalDateTime.now().plusMinutes(3));
                appUserService.saveUser(appUser);
                checkUserCode.setCodeStatus(CodeStatus.FAILED);
                codeService.saveCode(checkUserCode);
            }
            return new ResponseEntity<>("Авторизация не пройдена! Вы ввели некоректный код подтвеждения", HttpStatus.NOT_FOUND);
        }
        requestService.saveRequest(checkUserCode, true);
        LocalDateTime tokensTimeLife = LocalDateTime.now().plusMinutes(5);
        String token = Jwts.builder()
                .claim("login e-mail", login)
                .setExpiration(appUserService.convertToDateViaInstant(tokensTimeLife))
                .signWith(SignatureAlgorithm.HS256
                        , secretKey)
                .compact();
        checkUserCode.setCodeStatus(CodeStatus.APPROVED);
        codeService.saveCode(checkUserCode);
        return ResponseEntity.ok("Вы успешно ввели пароль!");
    }
    @Override
    public ResponseEntity<?> verifyToken(String token) {
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return ResponseEntity.ok(jwt.getBody().get("login"));
        } catch (ExpiredJwtException jwtException) {

            return new ResponseEntity<>("Время действия токена истек", HttpStatus.CONFLICT);
        } catch (UnsupportedJwtException jwtException) {

            return new ResponseEntity<>("Неподерживаемый токен", HttpStatus.CONFLICT);
        } catch (MalformedJwtException jwtException) {

            return new ResponseEntity<>("Некорректный токен", HttpStatus.CONFLICT);
        } catch (SignatureException signatureException) {

            return new ResponseEntity<>("Некорректная подпись в токене!", HttpStatus.CONFLICT);
        } catch (Exception exception) {

            return new ResponseEntity<>("unauthorized", HttpStatus.CONFLICT);
        }
    }
}
