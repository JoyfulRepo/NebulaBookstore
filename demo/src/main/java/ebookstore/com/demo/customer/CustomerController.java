package ebookstore.com.demo.customer;

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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    // Add
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        boolean isDeleted = customerService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteCustomerByName(@PathVariable String name) {
        boolean isDeleted = customerService.deleteByName(name);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        return customerService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update
    @PutMapping("/{id}/email")
    public ResponseEntity<Customer> updateCustomerEmail(@PathVariable Long id, @RequestParam String email) {
        try {
            Customer updatedCustomer = customerService.updateEmail(id, email);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<Customer> updateCustomerPhoneNumber(@PathVariable Long id, @RequestParam String phoneNumber) {
        try {
            Customer updatedCustomer = customerService.updatePhoneNumber(id, phoneNumber);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Customer> updateCustomerPassword(@PathVariable Long id, @RequestParam String password) {
        try {
            Customer updatedCustomer = customerService.updatePassword(id, password);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
