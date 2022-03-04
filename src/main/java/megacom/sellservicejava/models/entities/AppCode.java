package megacom.sellservicejava.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import megacom.sellservicejava.enums.CodeStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    @CreationTimestamp
    LocalDateTime startDate;
    LocalDateTime endDate;
    CodeStatus codeStatus;

    @ManyToOne
    @JoinColumn(name = "id_AppUser")
    AppUser appUser;


}
