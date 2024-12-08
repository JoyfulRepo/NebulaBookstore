package ebookstore.com.demo.activityLog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activityLogs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogService.findAll();
    }

    // Add
    @PostMapping
    public ActivityLog addActivityLog(@RequestBody ActivityLog activityLog) {
        return activityLogService.save(activityLog);
    }

    // Delete
    @DeleteMapping("/{logId}/{customerId}")
    public ResponseEntity<Void> deleteActivityLogById(@PathVariable Long logId, @PathVariable Long customerId) {
        ActivityLogId id = new ActivityLogId(logId, customerId);
        boolean isDeleted = activityLogService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{logId}/{customerId}")
    public ResponseEntity<ActivityLog> getActivityLogById(@PathVariable Long logId, @PathVariable Long customerId) {
        ActivityLogId id = new ActivityLogId(logId, customerId);
        return activityLogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/action/{actionType}")
    public ResponseEntity<List<ActivityLog>> getActivityLogByActionType(@PathVariable String actionType) {
        List<ActivityLog> activityLogs = activityLogService.findByActionType(actionType);
        if (activityLogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(activityLogs);
    }
}