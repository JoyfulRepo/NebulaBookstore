package ebookstore.com.demo.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ebookstore.com.demo.order.Order.PaymentStatus;
import ebookstore.com.demo.order.Order.Status;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByStatus(Status status);

    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);
}
