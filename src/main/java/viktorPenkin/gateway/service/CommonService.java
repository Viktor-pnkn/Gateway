package viktorPenkin.gateway.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import viktorPenkin.gateway.entity.Common;
import viktorPenkin.gateway.repository.CommonRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommonService {

    private final CommonRepository repo;

    public Common saveValue(Common common) {
        common.setDate(LocalDateTime.now());
        return repo.save(common);
    }

}
