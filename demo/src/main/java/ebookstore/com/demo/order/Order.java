package ebookstore.com.demo.order;

import java.math.BigDecimal;
import java.time.LocalDate;

import ebookstore.com.demo.cart.Cart;
import ebookstore.com.demo.customer.Customer;
import ebookstore.com.demo.employee.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "`ORDER`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Long id;

    @NotNull
    @Column(name = "OrderDate", nullable = false)
    private LocalDate orderDate;

    @NotNull
    @Column(name = "ExpectedArrival", nullable = false)
    private LocalDate expectedArrival;

    @NotBlank
    @Column(name = "Destination", nullable = false, length = 255)
    private String destination;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    @Column(name = "TotalAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentMethod", nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentStatus", nullable = false)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "CustomerID", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "CartID", referencedColumnName = "CartID"),
            @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    })
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    public enum Status {
        Pending, Shipping, Delivered, Cancelled
    }

    public enum PaymentMethod {
        Online_Banking, Cash, Credit_Card, Debit_Card
    }

    public enum PaymentStatus {
        Paid, Unpaid, Refunded
    }
}