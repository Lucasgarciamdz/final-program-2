package ar.edu.um.pcbuilder.services;

import ar.edu.um.pcbuilder.dtos.entities.devices.DeviceDto;
import ar.edu.um.pcbuilder.dtos.entities.sales.SaleDto;
import ar.edu.um.pcbuilder.dtos.entities.sales.SalePersonalizationDto;
import ar.edu.um.pcbuilder.dtos.warehouse.requests.ActivateUser;
import ar.edu.um.pcbuilder.dtos.warehouse.requests.Authenticate;
import ar.edu.um.pcbuilder.dtos.warehouse.requests.RegisterUser;
import ar.edu.um.pcbuilder.properties.WarehousePpties;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WarehouseSvc extends BaseSvc {

  public WarehouseSvc(WarehousePpties warehousePpties) {
    super(warehousePpties);
  }

  public static List<DeviceDto> requestDevices() {
    log.info("Requesting list of devices");
    List<DeviceDto> devices = makeRequest("/api/catedra/dispositivos", HttpMethod.GET, null, new ParameterizedTypeReference<List<DeviceDto>>() {});
    log.debug("Received devices: {}", devices);
    return devices;
  }

  public static SaleDto createSale(SaleDto saleDto) {
    log.info("Creating sale with details: {}", saleDto);
    SaleDto sale = makeRequest("/api/catedra/vender", HttpMethod.POST, saleDto, new ParameterizedTypeReference<SaleDto>() {});
    log.debug("Sale created: {}", sale);
    return sale;
  }

  public static List<SaleDto> listSales() {
    log.info("Requesting list of sales");
    List<SaleDto> sales = makeRequest("/api/catedra/ventas", HttpMethod.GET, null, new ParameterizedTypeReference<List<SaleDto>>() {});
    log.debug("Received sales: {}", sales);
    return sales;
  }

  public static SalePersonalizationDto getSaleDetail(Long saleId) {
    log.info("Requesting details for sale ID: {}", saleId);
    SalePersonalizationDto saleDetail = makeRequest("/api/catedra/venta/" + saleId, HttpMethod.GET, null, new ParameterizedTypeReference<SalePersonalizationDto>() {});
    log.debug("Received sale detail: {}", saleDetail);
    return saleDetail;
  }

  public static String authenticate(String username, String password) {
    log.info("Authenticating user: {}", username);
    Authenticate body = new Authenticate(username, password, false);
    String token = makeRequest("/api/authenticate", HttpMethod.POST, body, new ParameterizedTypeReference<String>() {});
    log.debug("Received token: {}", token);
    return token;
  }

  public static void activateUser(String login, String email, String nombres, String descripcion) {
    log.info("Activating user: {}", login);
    ActivateUser body = new ActivateUser(login, email, nombres, descripcion);
    makeRequest("/api/activar", HttpMethod.POST, body, new ParameterizedTypeReference<Void>() {});
    log.debug("User activated: {}", login);
  }

  public static void registerUser(String login, String email, String password, String langKey) {
    log.info("Registering user: {}", login);
    RegisterUser body = new RegisterUser(login, email, password, langKey);
    makeRequest("/api/register", HttpMethod.POST, body, new ParameterizedTypeReference<Void>() {});
    log.debug("User registered: {}", login);
  }
}