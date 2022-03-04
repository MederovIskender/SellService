package megacom.sellservicejava;

import megacom.sellservicejava.services.AppUserService;
import megacom.sellservicejava.services.impl.AppUserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SellServiceJavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(SellServiceJavaApplication.class, args);
    }

}
