package megacom.sellservicejava.services.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.enums.CodeStatus;
import megacom.sellservicejava.mappers.AppCodeMapper;
import megacom.sellservicejava.mappers.AppUserMapper;
import megacom.sellservicejava.models.dtos.AppCodeDtos.AppCodeEntityDto;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.entities.AppCode;
import megacom.sellservicejava.repos.CodeRepo;
import megacom.sellservicejava.services.CodeService;
import megacom.sellservicejava.services.SendSimpleMessage;
import org.apache.catalina.User;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class CodeServiceImpl implements CodeService {

    CodeRepo codeRepo;
    SendSimpleMessage sendSimpleMessage;

    @Override
    public void saveCode(AppCodeEntityDto appCodeEntityDto) {
        codeRepo.save(AppCodeMapper.INSTANCE.AppCodeEntityDtoToAppCode(appCodeEntityDto));
    }

    @Override
    public void sendCode(AppUserCreationDto appUserCreationDto) {
        AppCode lastCode = findLastCode(appUserCreationDto);
        if (Objects.nonNull(lastCode)){
            lastCode.setCodeStatus(CodeStatus.CANCELLED);
            //codeREpo.save(lastCode);
        }

        int code = (int) ((Math.random()*9000)+1000);
        String hashedCode = BCrypt.hashpw(Integer.toString(code),BCrypt.gensalt());

        AppCode savedCode = new AppCode();
        savedCode.setCode(hashedCode);
        savedCode.setEndDate(LocalDateTime.now().plusMinutes(3));
        savedCode.setCodeStatus(CodeStatus.NEW);
        savedCode.setAppUser(AppUserMapper.INSTANCE.AppUserCreateDtoToAppUser(appUserCreationDto));
        //AppCodeRepo.save(savedCode);
        sendSimpleMessage.sendSimpleMessage(appUserCreationDto.getLogin(), Integer.toString(code));
    }

    @Override
    public AppCode findLastCode(AppUserCreationDto appUserCreationDto) {
        return codeRepo.findByAppUserAndCodeStatus(AppUserMapper.INSTANCE.AppUserCreateDtoToAppUser(appUserCreationDto), CodeStatus.NEW);
    }
}
