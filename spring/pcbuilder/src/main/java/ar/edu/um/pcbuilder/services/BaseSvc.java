package ar.edu.um.pcbuilder.services;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import ar.edu.um.pcbuilder.properties.WarehousePpties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class BaseSvc {

  WarehousePpties warehousePpties;
  private static String baseUrl = "http://localhost:8080";

  private static final HttpHeaders headers = new HttpHeaders();
  private static RestClient warehouseClient;

  public BaseSvc(WarehousePpties warehousePpties) {
    this.warehousePpties = warehousePpties;
    if (warehousePpties.baseUrl() != null) {
      baseUrl = warehousePpties.baseUrl();
    }
    warehouseClient = RestClient.builder().baseUrl(baseUrl)
        .defaultHeaders(header -> headers.setAll(headers.toSingleValueMap())).build();
    headers.set("Content-Type", "application/json");
    headers.set("Authorization", "Bearer " + warehousePpties.jwt());

  }

  public static <T> T makeRequest(String url, HttpMethod method, Object body, ParameterizedTypeReference<T> responseType) {

    log.info("Making request to: " + url);
    try {
      return warehouseClient.method(method).uri(url).body(body).retrieve().toEntity(responseType)
          .getBody();

    } catch (HttpClientErrorException e) {
      handleHttpError(e);
      return null;
    } catch (HttpServerErrorException e) {
      handleHttpError(e);
      return null;
    } catch (RestClientException e) {
      throw new RuntimeException("Error making request: " + e.getMessage());
    }
  }


  private static void handleHttpError(HttpStatusCodeException e) {
    HttpStatusCode status = e.getStatusCode();
    switch (status) {
      case UNAUTHORIZED:
        throw new RuntimeException("Unauthorized: " + e.getResponseBodyAsString());
      case FORBIDDEN:
        throw new RuntimeException("Forbidden: " + e.getResponseBodyAsString());
      case NOT_FOUND:
        throw new RuntimeException("Not Found: " + e.getResponseBodyAsString());
      case INTERNAL_SERVER_ERROR:
        throw new RuntimeException("Internal Server Error: " + e.getResponseBodyAsString());
      default:
        throw new RuntimeException("HTTP Error: " + e.getResponseBodyAsString());
    }
  }
}