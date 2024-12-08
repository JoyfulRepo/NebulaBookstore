package ebookstore.com.demo.cart;

import java.time.LocalDate;
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

import ebookstore.com.demo.order.Order;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.findAll();
    }

    // Add
    @PostMapping
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @PostMapping("/{cartId}/{customerId}/cart")
    public ResponseEntity<Order> finalizeCart(@PathVariable Long cartId, @PathVariable Long customerId,
            @RequestParam String destination,
            @RequestParam Order.PaymentMethod paymentMethod) {
        CartId id = new CartId(cartId, customerId);
        Order order = cartService.finalizeCart(id, destination, paymentMethod);
        return ResponseEntity.ok(order);
    }

    // Delete
    @DeleteMapping("/{cartId}/{customerId}")
    public ResponseEntity<Void> deleteCartById(@PathVariable Long cartId, @PathVariable Long customerId) {
        CartId id = new CartId(cartId, customerId);
        boolean isDeleted = cartService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{cartId}/{customerId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId, @PathVariable Long customerId) {
        CartId id = new CartId(cartId, customerId);
        return cartService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update
    @PutMapping("/{cartId}/{customerId}/update")
    public ResponseEntity<Cart> updateCartLastUpdated(@PathVariable Long cartId, @PathVariable Long customerId,
            @RequestParam String lastUpdated) {
        try {
            LocalDate parsedDate = LocalDate.parse(lastUpdated);
            CartId id = new CartId(cartId, customerId);
            Cart updatedCart = cartService.updateLastUpdated(id, parsedDate);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Add Book to Cart
    @PutMapping("/{cartId}/{customerId}/books/{bookId}")
    public ResponseEntity<Cart> addBookToCart(@PathVariable Long cartId, @PathVariable Long customerId,
            @PathVariable Long bookId) {
        try {
            CartId id = new CartId(cartId, customerId);
            Cart updatedCart = cartService.addBookToCart(id, bookId);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}