package viktorPenkin.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import viktorPenkin.gateway.dto.CommonDTO;
import viktorPenkin.gateway.dto.NumDTO;
import viktorPenkin.gateway.exception.IncorrectNumberException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = {"gateway", "gatewaytest"})
public class MainController {

    private RestTemplate template = new RestTemplate();

    @PostMapping
    public NumDTO getValueHTTP(HttpServletRequest request) {
        Long value = Long.valueOf(request.getParameter("value"));
        String type = request.getParameter("type");
        if (type.equals("FACT") && value > 20) {
            throw new IncorrectNumberException("Number should be less than 21 to calculate factorial");
        }
        if (type.equals("FIB") && value <=2) {
            throw new IncorrectNumberException("Number should be more than 2 to calculate fibonacci");
        }
        CommonDTO commonDTO = new CommonDTO(type, value);
        return getValue(commonDTO);
    }

    NumDTO getValue(CommonDTO commonDTO) {
        if (commonDTO.getType().equals("FACT")) {
            ResponseEntity<NumDTO> forEntity = template.getForEntity("http://localhost:8081/factorial/" +
                            commonDTO.getValue(),
                    NumDTO.class);
            return forEntity.getBody();
        }
        if (commonDTO.getType().equals("FIB")) {
            ResponseEntity<NumDTO> forEntity = template.getForEntity("http://localhost:8082/fibonacci/" +
                            commonDTO.getValue(),
                    NumDTO.class);
            return forEntity.getBody();
        }
        return null;
    }
}
