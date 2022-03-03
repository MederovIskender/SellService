package megacom.sellservicejava.services;

import megacom.sellservicejava.models.dtos.AppCodeDtos.AppCodeEntityDto;

public interface RequestService {
    void saveRequest (AppCodeEntityDto appCodeEntityDto, boolean success);
    int countFailedAttempts (AppCodeEntityDto appCodeEntityDto);
}
