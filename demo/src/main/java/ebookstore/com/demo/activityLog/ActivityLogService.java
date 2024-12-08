package ebookstore.com.demo.activityLog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    // Add
    public ActivityLog save(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    // Get
    public List<ActivityLog> findAll() {
        return activityLogRepository.findAll();
    }

    public Optional<ActivityLog> findById(ActivityLogId id) {
        return activityLogRepository.findById(id);
    }

    public List<ActivityLog> findByActionType(String actionType) {
        return activityLogRepository.findByActionType(actionType);
    }

    // Delete
    public boolean deleteById(ActivityLogId id) {
        Optional<ActivityLog> activityLogOptional = activityLogRepository.findById(id);
        if (activityLogOptional.isPresent()) {
            activityLogRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}