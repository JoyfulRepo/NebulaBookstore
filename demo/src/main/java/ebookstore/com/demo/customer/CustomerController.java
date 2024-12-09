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

import ebookstore.com.demo.cart.Cart;
import ebookstore.com.demo.review.Review;

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
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @PostMapping("/{customerId}/books/{bookId}/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Integer customerId, @PathVariable Integer bookId,
            @RequestParam Integer rating, @RequestParam String comment) {
        try {
            Review review = customerService.addReview(bookId, customerId, rating, comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(review);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer id) {
        boolean isDeleted = customerService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String name) {
        List<Customer> customers = customerService.findByName(name);
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(customers);
    }

    // Update
    @PutMapping("/{id}/email")
    public ResponseEntity<Customer> updateCustomerEmail(@PathVariable Integer id, @RequestParam String email) {
        try {
            Customer updatedCustomer = customerService.updateEmail(id, email);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<Customer> updateCustomerPhoneNumber(@PathVariable Integer id,
            @RequestParam String phoneNumber) {
        try {
            Customer updatedCustomer = customerService.updatePhoneNumber(id, phoneNumber);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Customer> updateCustomerPassword(@PathVariable Integer id, @RequestParam String password) {
        try {
            Customer updatedCustomer = customerService.updatePassword(id, password);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{customerId}/cart/books/{bookId}")
    public ResponseEntity<Cart> addBookToCart(@PathVariable Integer customerId, @PathVariable Integer bookId) {
        try {
            Cart updatedCart = customerService.addBookToCart(customerId, bookId);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
