package megacom.sellservicejava.models.dtos.appUserDtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppUserCreationDto {
    String name;
    String login;
}
