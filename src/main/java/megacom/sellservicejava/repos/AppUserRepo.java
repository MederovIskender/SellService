package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByLogin(String login);
}
