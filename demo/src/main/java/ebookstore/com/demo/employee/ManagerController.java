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
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/staff")
    public List<Staff> getAllStaff() {
        return managerService.getAllStaff();
    }

    @PostMapping
    public Manager addManager(@RequestBody Manager manager) {
        return managerService.save(manager);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        Manager manager = managerService.findById(id);
        if (manager != null) {
            return ResponseEntity.ok(manager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManagerById(@PathVariable Long id) {
        managerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}