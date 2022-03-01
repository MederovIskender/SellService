package megacom.sellservicejava.models.dtos.productDtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import megacom.sellservicejava.models.entities.Category;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntityDto {
        long id;
        String productName;
        String barcode;
        Category category;
        boolean active;
}
