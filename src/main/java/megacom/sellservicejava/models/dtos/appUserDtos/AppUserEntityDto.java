package megacom.sellservicejava.models.dtos.appUserDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserEntityDto {
    long id;
    String name;
    String login;
    boolean active;
    LocalDateTime blockEndDate;
}
