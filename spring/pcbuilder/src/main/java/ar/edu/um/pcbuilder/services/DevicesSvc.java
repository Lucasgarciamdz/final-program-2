package ar.edu.um.pcbuilder.services;

import ar.edu.um.pcbuilder.dtos.entities.devices.DeviceDto;
import ar.edu.um.pcbuilder.properties.WharehousePpties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DevicesSvc extends BaseSvc {

  public DevicesSvc(WharehousePpties wharehousePpties) {
    super(wharehousePpties);
  }

  public List<DeviceDto> getDevices() {
    String json = "[{\"id\":1,\"code\":\"DEV123\",\"name\":\"Gaming PC\",\"description\":\"High-end gaming PC\",\"basePrice\":1500.00,\"currency\":\"USD\",\"characteristics\":[{\"id\":1,\"deviceId\":1,\"name\":\"Processor\",\"description\":\"Intel Core i9\"},{\"id\":2,\"deviceId\":1,\"name\":\"RAM\",\"description\":\"32GB DDR4\"}],\"personalizations\":[{\"id\":1,\"deviceId\":1,\"name\":\"Color\",\"description\":\"Choose your case color\"}],\"options\":[{\"id\":1,\"personalizationId\":1,\"code\":\"OPT_RED\",\"name\":\"Red\",\"description\":\"Red case color\",\"additionalPrice\":50.00},{\"id\":2,\"personalizationId\":1,\"code\":\"OPT_BLUE\",\"name\":\"Blue\",\"description\":\"Blue case color\",\"additionalPrice\":50.00}],\"additionalItems\":[{\"id\":1,\"deviceId\":1,\"name\":\"Extended Warranty\",\"description\":\"2-year extended warranty\",\"price\":100.00,\"freePrice\":0.00}]}]";
    return convertJsonToDeviceDtoList(json);
  }

  private List<DeviceDto> convertJsonToDeviceDtoList(String json) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(json, new TypeReference<>() {
      });
    } catch (Exception e) {
      log.error("Error converting JSON to DeviceDto list", e);
      return Collections.emptyList();
    }
  }
}