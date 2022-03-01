package megacom.sellservicejava.models.entities;

import lombok.Value;
import megacom.sellservicejava.enums.CodeStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class TheCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    LocalDateTime startDate;
    LocalDateTime endDate;
    CodeStatus codeStatus;
    AppUser user;


}
