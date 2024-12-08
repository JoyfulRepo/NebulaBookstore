package ebookstore.com.demo.activityLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, ActivityLogId> {

    List<ActivityLog> findByActionType(String actionType);
}