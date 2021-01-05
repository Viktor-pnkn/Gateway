package viktorPenkin.gateway.controller;

import org.junit.jupiter.api.Test;
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
    private Random r = new Random();

    @Test
    public void testGetBody() {
        int i = r.nextInt();
        HttpEntity<CommonDTO> request = new HttpEntity<>(new CommonDTO("FACT", i));
        ResponseEntity<NumDTO> forEntity = template.exchange("http://localhost:8080/gateway/test", HttpMethod.POST,
                request, NumDTO.class);

        NumDTO numDTO = forEntity.getBody();

        assert numDTO != null;
        Integer actual = numDTO.getValue();

            int expected = 1;
            for (int j = 1; j < i + 1; j++) {
                expected *= j;
            }
            assertEquals(expected, actual);
    }
}
