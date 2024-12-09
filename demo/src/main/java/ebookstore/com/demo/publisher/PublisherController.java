package ebookstore.com.demo.publisher;

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
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.findAll();
    }

    // Add
    @PostMapping
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisherById(@PathVariable Integer id) {
        boolean isDeleted = publisherService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Integer id) {
        return publisherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Publisher>> getPublisherByName(@PathVariable String name) {
        List<Publisher> publishers = publisherService.findByName(name);
        if (publishers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(publishers);
    }

    // Update
    @PutMapping("/{id}/address")
    public ResponseEntity<Publisher> updatePublisherAddress(@PathVariable Integer id, @RequestParam String address) {
        try {
            Publisher publisher = publisherService.updateAddress(id, address);
            return ResponseEntity.ok(publisher);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/phoneNumber")
    public ResponseEntity<Publisher> updatePublisherPhoneNumber(@PathVariable Integer id,
            @RequestParam String phoneNumber) {
        try {
            Publisher publisher = publisherService.updatePhoneNumber(id, phoneNumber);
            return ResponseEntity.ok(publisher);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
