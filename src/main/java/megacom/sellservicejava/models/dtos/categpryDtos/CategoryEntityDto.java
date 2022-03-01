package megacom.sellservicejava.models.dtos.categpryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntityDto {

    long id;
    String CategoryName;
    boolean active;
}
