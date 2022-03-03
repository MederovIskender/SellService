package megacom.sellservicejava.models.dtos.AppCodeDtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import megacom.sellservicejava.enums.CodeStatus;
import megacom.sellservicejava.models.entities.AppUser;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
public class AppCodeEntityDto {
    Long id;
    String code;
    LocalDateTime startDate;
    LocalDateTime endDate;
    CodeStatus codeStatus;
    AppUser appUser;
}
