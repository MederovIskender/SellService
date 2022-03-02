package megacom.sellservicejava.mappers;

import megacom.sellservicejava.models.dtos.appUserDtos.AppUserCreationDto;
import megacom.sellservicejava.models.dtos.appUserDtos.AppUserEntityDto;
import megacom.sellservicejava.models.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);
    AppUser AppUserCreateDtoToAppUser(AppUserCreationDto appUserCreationDto);
    AppUserCreationDto AppUserToAppUserCreateDto(AppUser appUser);
    AppUser AppUserEntityDtoToAppUser(AppUserEntityDto appUserEntityDto);
    AppUserEntityDto AppUserToAppUserEntityDto(AppUser appUser);
}
