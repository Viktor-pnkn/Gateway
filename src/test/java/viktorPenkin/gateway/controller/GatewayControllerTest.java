package viktorPenkin.gateway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import viktorPenkin.gateway.dto.CommonDTO;
import viktorPenkin.gateway.dto.NumDTO;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GatewayControllerTest {

    private RestTemplate template = new RestTemplate();
    private Random r = new Random(10L);
    @Autowired
    private MainController controller;

    @Test
    public void testGetBody() {
        Long i = Long.valueOf(r.nextInt(20));
        NumDTO numDTO = controller.getValue(new CommonDTO("FACT", i));
        assert numDTO != null;
        Long actual = numDTO.getValue();
            Long expected = 1L;
            for (int j = 1; j < i + 1; j++) {
                expected *= j;
            }
            assertEquals(expected, actual);
    }
}
