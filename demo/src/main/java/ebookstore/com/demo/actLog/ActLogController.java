package ebookstore.com.demo.actLog;

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
@RequestMapping("/actLogs")
public class ActLogController {

    @Autowired
    private ActLogService actLogService;

    @GetMapping
    public List<ActLog> getAllActLogs() {
        return actLogService.findAll();
    }

    // Add
    @PostMapping
    public ActLog addActLog(@RequestBody ActLog actLog) {
        return actLogService.save(actLog);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActLogById(@PathVariable Long id) {
        boolean isDeleted = actLogService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<ActLog> getActLogById(@PathVariable Long id) {
        return actLogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/action/{action}")
    public ResponseEntity<List<ActLog>> getActLogByAction(@PathVariable String action) {
        List<ActLog> actLogs = actLogService.findByAction(action);
        if (actLogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(actLogs);
    }

    // Update
    // No editing
}
