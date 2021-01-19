package viktorPenkin.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import viktorPenkin.gateway.dto.CommonDTO;
import viktorPenkin.gateway.exception.IncorrectNumberException;
import viktorPenkin.gateway.mapper.CommonMapper;
import viktorPenkin.gateway.service.CommonService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = {"gateway", "gatewaytest"})
@RequiredArgsConstructor
public class MainController {

    private final CommonService service;
    private final CommonMapper commonMapper = new CommonMapper();
    private RestTemplate template = new RestTemplate();

    @PostMapping
    public CommonDTO getValueHTTP(HttpServletRequest request) {
        Long value = Long.valueOf(request.getParameter("value"));
        String type = request.getParameter("type");
        if (type.equals("FACT") && value > 20) {
            throw new IncorrectNumberException("Number should be less than 21 to calculate factorial");
        }
        if (type.equals("FIB") && (value <= 2 || value >= 94)) {
            throw new IncorrectNumberException("Number should be more than 2 and less than 94 to calculate fibonacci");
        }
        CommonDTO commonDTO = getValue(new CommonDTO(type, value));
        service.saveValue(commonMapper.toEntity(commonDTO));
        return commonDTO;
    }

    CommonDTO getValue(CommonDTO commonDTO) {
        if (commonDTO.getType().equals("FACT")) {
            ResponseEntity<CommonDTO> forEntity = template.getForEntity("http://localhost:8081/factorial/" +
                    commonDTO.getValue(), CommonDTO.class);
            return forEntity.getBody();
        }
        if (commonDTO.getType().equals("FIB")) {
            ResponseEntity<CommonDTO> forEntity = template.getForEntity("http://localhost:8082/fibonacci/" +
                            commonDTO.getValue(),
                    CommonDTO.class);
            return forEntity.getBody();
        }
        return null;
    }
}
