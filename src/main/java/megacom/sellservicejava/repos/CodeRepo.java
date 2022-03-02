package megacom.sellservicejava.repos;

import megacom.sellservicejava.enums.CodeStatus;
import megacom.sellservicejava.models.entities.AppCode;
import megacom.sellservicejava.models.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<AppCode,Long> {
    AppCode findByAppUserAndCodeStatus(AppUser appUser, CodeStatus codeStatus);
}
