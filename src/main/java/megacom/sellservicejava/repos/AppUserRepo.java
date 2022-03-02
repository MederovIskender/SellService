package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser,Long> {
}
