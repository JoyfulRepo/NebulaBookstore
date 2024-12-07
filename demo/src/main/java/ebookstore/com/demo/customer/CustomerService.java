package ebookstore.com.demo.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ebookstore.com.demo.book.Book;
import ebookstore.com.demo.book.BookRepository;
import ebookstore.com.demo.cart.Cart;
import ebookstore.com.demo.cart.CartRepository;
import ebookstore.com.demo.review.Review;
import ebookstore.com.demo.review.ReviewService;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    // Add
    public Customer save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Review addReview(Long bookId, Long customerId, Integer rating, String comment) {
        return reviewService.addReview(bookId, customerId, rating, comment);
    }

    public Cart addBookToCart(Long customerId, Long bookId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }
        cart.getBooks().add(book);
        return cartRepository.save(cart);
    }

    // Get
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    // Delete
    public boolean deleteById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Customer updateEmail(Long id, String email) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setEmail(email);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    public Customer updatePhoneNumber(Long id, String phoneNumber) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setPhoneNumber(phoneNumber);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    public Customer updatePassword(Long id, String password) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setPassword(passwordEncoder.encode(password));
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer updatedCustomer = customerOptional.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
            updatedCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
            return customerRepository.save(updatedCustomer);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }
}