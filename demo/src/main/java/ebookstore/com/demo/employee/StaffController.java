package ebookstore.com.demo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping
    public Staff addStaff(@RequestBody Staff staff) {
        return staffService.save(staff);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.findById(id);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffById(@PathVariable Long id) {
        staffService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<List<String>> getWorkSchedule(@PathVariable Long id) {
        List<String> schedule = staffService.getWorkSchedule(id);
        return ResponseEntity.ok(schedule);
    }
}