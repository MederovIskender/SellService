package megacom.sellservicejava.models.dtos.OperationDtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserEntityDto;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDto {

    Long id;
    LocalDateTime addDate;
    double totalAmount;
    double change;
    AppUserEntityDto user;
    double cash;
}
