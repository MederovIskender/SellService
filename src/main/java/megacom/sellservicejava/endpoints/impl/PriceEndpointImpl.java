package megacom.sellservicejava.endpoints.impl;

import megacom.sellservicejava.endpoints.AppUserEndPoint;
import megacom.sellservicejava.endpoints.PriceEndpoint;
import megacom.sellservicejava.models.dtos.priceDtos.PriceCreateDto;
import megacom.sellservicejava.models.entities.Price;
import megacom.sellservicejava.services.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PriceEndpointImpl implements PriceEndpoint {

    PriceService priceService;
    AppUserEndPoint appUserEndPoint;

    public PriceEndpointImpl(PriceService priceService, AppUserEndPoint appUserEndPoint) {
        this.priceService = priceService;
        this.appUserEndPoint = appUserEndPoint;
    }

    @Override
    public ResponseEntity<?> savePrice(String token, PriceCreateDto priceCreateDto) {
        ResponseEntity<?> responseEntity = appUserEndPoint.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Price price = priceService.savePrice(priceCreateDto);
        if(Objects.isNull(price)){
            return new ResponseEntity<>("Цена не была создана", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Цена на была успешно установлена");
    }
}
