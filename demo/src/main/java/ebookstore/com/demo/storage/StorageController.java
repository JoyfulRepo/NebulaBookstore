package ebookstore.com.demo.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storages")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping
    public List<Storage> getAllStorages() {
        return storageService.findAll();
    }

    // Add
    @PostMapping
    public Storage addStorage(@RequestBody Storage storage) {
        return storageService.save(storage);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStorageById(@PathVariable Integer id) {
        boolean isDeleted = storageService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Storage> getStorageById(@PathVariable Integer id) {
        return storageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update
    @PutMapping("/{id}/capacity")
    public ResponseEntity<Storage> updateStorageCapacity(@PathVariable Integer id, @RequestParam Integer capacity) {
        try {
            Storage updatedStorage = storageService.updateCapacity(id, capacity);
            return ResponseEntity.ok(updatedStorage);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
