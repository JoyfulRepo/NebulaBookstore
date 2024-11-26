package ebookstore.com.demo.actLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActLogRepository extends JpaRepository<ActLog, Long> {

    List<ActLog> findByAction(String action);
}
