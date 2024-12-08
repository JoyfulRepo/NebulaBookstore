package ebookstore.com.demo.order;

import java.math.BigDecimal;
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

import ebookstore.com.demo.order.Order.PaymentMethod;
import ebookstore.com.demo.order.Order.PaymentStatus;
import ebookstore.com.demo.order.Order.Status;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    // Add
    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrderByStatus(@PathVariable Status status) {
        List<Order> orders = orderService.findByStatus(status);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/paymentStatus/{paymentStatus}")
    public ResponseEntity<List<Order>> getOrderByPaymentStatus(@PathVariable PaymentStatus paymentStatus) {
        List<Order> orders = orderService.findByPaymentStatus(paymentStatus);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(orders);
    }

    // Update
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam Status status) {
        try {
            Order updatedOrder = orderService.updateStatus(id, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/paymentStatus")
    public ResponseEntity<Order> updateOrderPaymentStatus(@PathVariable Long id,
            @RequestParam PaymentStatus paymentStatus) {
        try {
            Order updatedOrder = orderService.updatePaymentStatus(id, paymentStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/deliveryDate")
    public ResponseEntity<Order> updateOrderDeliveryDate(@PathVariable Long id, @RequestParam String deliveryDate) {
        try {
            LocalDate parsedDate = LocalDate.parse(deliveryDate);
            Order updatedOrder = orderService.updateDeliveryDate(id, parsedDate);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/destination")
    public ResponseEntity<Order> updateOrderDestination(@PathVariable Long id, @RequestParam String destination) {
        try {
            Order updatedOrder = orderService.updateDestination(id, destination);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/total")
    public ResponseEntity<Order> updateOrderTotal(@PathVariable Long id, @RequestParam BigDecimal total) {
        try {
            Order updatedOrder = orderService.updateTotal(id, total);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/paymentMethod")
    public ResponseEntity<Order> updateOrderPaymentMethod(@PathVariable Long id,
            @RequestParam PaymentMethod paymentMethod) {
        try {
            Order updatedOrder = orderService.updatePaymentMethod(id, paymentMethod);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
