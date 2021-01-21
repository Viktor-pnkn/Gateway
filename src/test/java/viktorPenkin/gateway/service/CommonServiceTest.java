package viktorPenkin.gateway.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import viktorPenkin.gateway.entity.Common;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CommonServiceTest {

    @Autowired
    private CommonService service;


    @Test
    @Rollback
    @Transactional
    public void saveValue() {
        Common common = new Common(
                null,
                "test",
                null,
                100L
        );
        Common actual = service.saveValue(common);

        assertNotNull(actual.getId());
        assertNotNull(actual.getDate());
    }
}
