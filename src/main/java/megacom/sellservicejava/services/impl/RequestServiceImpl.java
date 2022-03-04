package megacom.sellservicejava.services.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.mappers.AppCodeMapper;
import megacom.sellservicejava.models.dtos.AppCodeDtos.AppCodeEntityDto;
import megacom.sellservicejava.models.entities.Request;
import megacom.sellservicejava.repos.RequestRepo;
import megacom.sellservicejava.services.RequestService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RequestServiceImpl implements RequestService {

    RequestRepo requestRepo;
    @Override
    public void saveRequest(AppCodeEntityDto appCodeEntityDto, boolean success) {
        Request request = new Request();
        request.setAppCode(AppCodeMapper.INSTANCE.AppCodeEntityDtoToAppCode(appCodeEntityDto));
        request.setSuccess(success);
        requestRepo.save(request);
    }

    @Override
    public int countFailedAttempts(AppCodeEntityDto appCodeEntityDto) {
        return requestRepo.countByAppCodeAndSuccess(AppCodeMapper.INSTANCE.AppCodeEntityDtoToAppCode(appCodeEntityDto), false) ;
    }
}
