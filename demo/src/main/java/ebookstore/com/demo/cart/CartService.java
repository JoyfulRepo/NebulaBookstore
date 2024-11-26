package ebookstore.com.demo.cart;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Add
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get
    public List<Cart> findAll() {
        return (List<Cart>) cartRepository.findAll();
    }

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    // Delete
    public boolean deleteById(Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            cartRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Cart updateLastUpdated(Long id, LocalDate lastUpdated) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setLastUpdated(lastUpdated);
            return cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found with id: " + id);
        }
    }
}
