package ebookstore.com.demo.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebookstore.com.demo.order.Order.PaymentStatus;
import ebookstore.com.demo.order.Order.Status;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByStatus(Status status);

    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);
}
