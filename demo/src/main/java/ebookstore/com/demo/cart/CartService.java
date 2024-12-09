package ebookstore.com.demo.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebookstore.com.demo.book.Book;
import ebookstore.com.demo.book.BookRepository;
import ebookstore.com.demo.order.Order;
import ebookstore.com.demo.order.OrderRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    // Add
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findById(CartId id) {
        return cartRepository.findById(id);
    }

    // Delete
    public boolean deleteById(CartId id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            cartRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Cart updateLastUpdated(CartId id, LocalDate lastUpdated) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setLastUpdated(lastUpdated);
            return cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found with id: " + id);
        }
    }

    public Cart addBookToCart(CartId cartId, Integer bookId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        cart.getBooks().add(book);
        return cartRepository.save(cart);
    }

    public Order finalizeCart(CartId cartId, String destination, Order.PaymentMethod paymentMethod) {
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
        order.setDestination(destination);
        order.setTotalAmount(calculateTotal(cart));
        order.setStatus(Order.Status.Pending);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(Order.PaymentStatus.Unpaid);
        order.setCustomer(cart.getCustomer());
        order.setCart(cart);
        return order;
    }

    private BigDecimal calculateTotal(Cart cart) {
        return cart.getBooks().stream()
                .map(Book::getPrice)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }
}