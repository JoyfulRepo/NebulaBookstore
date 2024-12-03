package ebookstore.com.demo.cart;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebookstore.com.demo.book.Book;
import ebookstore.com.demo.order.Order;
import ebookstore.com.demo.order.OrderRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

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

    public Order finalizeCart(Long cartId, String destination, Order.PaymentMethod paymentMethod) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Order order = createOrderFromCart(cart, destination, paymentMethod);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Cart not found with id: " + cartId);
        }
    }

    private Order createOrderFromCart(Cart cart, String destination, Order.PaymentMethod paymentMethod) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setDestination(destination); // Set the actual destination
        order.setTotal(calculateTotal(cart));
        order.setStatus(Order.Status.PENDING);
        order.setPaymentMethod(paymentMethod); // Set the actual payment method
        order.setPaymentStatus(Order.PaymentStatus.UNPAID);
        order.setCustomer(cart.getCustomer());
        order.setBooks(cart.getBooks());
        return order;
    }

    private Double calculateTotal(Cart cart) {
        return cart.getBooks().stream().mapToDouble(Book::getPrice).sum();
    }
}
