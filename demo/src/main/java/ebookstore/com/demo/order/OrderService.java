package ebookstore.com.demo.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebookstore.com.demo.discount.Discount;
import ebookstore.com.demo.discount.DiscountRepository;
import ebookstore.com.demo.order.Order.PaymentMethod;
import ebookstore.com.demo.order.Order.PaymentStatus;
import ebookstore.com.demo.order.Order.Status;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Add
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    // Get
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByStatus(Status status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> findByPaymentStatus(PaymentStatus paymentStatus) {
        return orderRepository.findByPaymentStatus(paymentStatus);
    }

    // Delete
    public boolean deleteById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Order updateStatus(Integer id, Status status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public Order updatePaymentStatus(Integer id, PaymentStatus paymentStatus) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setPaymentStatus(paymentStatus);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public Order updateDeliveryDate(Integer id, LocalDate deliveryDate) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setExpectedArrival(deliveryDate);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public Order updateDestination(Integer id, String destination) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setDestination(destination);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public Order updateTotal(Integer id, BigDecimal total) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setTotalAmount(total);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public Order updatePaymentMethod(Integer id, PaymentMethod paymentMethod) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setPaymentMethod(paymentMethod);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }
}
