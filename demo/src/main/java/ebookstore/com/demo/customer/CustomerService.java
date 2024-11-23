package ebookstore.com.demo.customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Add
    public Customer save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    // Get
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    public Optional<Customer> findByJoinDate(LocalDate joinDate) {
        return customerRepository.findByJoinDate(joinDate);
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

    public boolean deleteByName(String name) {
        Optional<Customer> customerOptional = customerRepository.findByName(name);
        if (customerOptional.isPresent()) {
            customerRepository.deleteByName(name);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteByJoinDate(LocalDate joinDate) {
        Optional<Customer> customerOptional = customerRepository.findByJoinDate(joinDate);
        if (customerOptional.isPresent()) {
            customerRepository.deleteByJoinDate(joinDate);
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
}