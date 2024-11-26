package ebookstore.com.demo.order;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private LocalDate deliveryDate;

    @NotBlank
    private String destination;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    private Double total;

    @NotNull
    private Status status;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private PaymentStatus paymentStatus;

    public Order() {
    }

    public Order(
            LocalDate orderDate,
            LocalDate deliveryDate,
            String destination,
            Double total,
            Status status,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.destination = destination;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public Order(
            Long id,
            LocalDate orderDate,
            LocalDate deliveryDate,
            String destination,
            Double total,
            Status status,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.destination = destination;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public enum Status {
        PENDING, DELIVERED, CANCELLED
    }

    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD
    }

    public enum PaymentStatus {
        PAID, UNPAID, REFUNDED
    }
}
