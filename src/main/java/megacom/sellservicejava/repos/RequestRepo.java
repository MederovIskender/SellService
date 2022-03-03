package megacom.sellservicejava.repos;

import megacom.sellservicejava.models.entities.AppCode;
import megacom.sellservicejava.models.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {
    int countByAppCodeAndSuccess(AppCode appCode, boolean success);
}
