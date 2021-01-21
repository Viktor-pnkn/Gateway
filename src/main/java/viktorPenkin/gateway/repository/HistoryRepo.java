package viktorPenkin.gateway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import viktorPenkin.gateway.entity.Common;

@Repository("HistoryRepo")
public interface HistoryRepo extends CrudRepository<Common, Long> {
}
