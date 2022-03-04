package megacom.sellservicejava;

import megacom.sellservicejava.services.AppUserService;
import megacom.sellservicejava.services.impl.AppUserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SellServiceJavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(SellServiceJavaApplication.class, args);
    }

}
