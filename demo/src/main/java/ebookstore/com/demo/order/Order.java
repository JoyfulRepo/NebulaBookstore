package ebookstore.com.demo.order;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.book.Book;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "`ORDER`") // Escape the table name to avoid conflicts with reserved keywords
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Long id;

    @NotNull
    @Column(name = "OrderDate")
    private LocalDate orderDate;

    @NotNull
    @Column(name = "ExpectedArrival")
    private LocalDate expectedArrival;

    @NotBlank
    @Column(name = "Destination")
    private String destination;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    @Column(name = "TotalAmount")
    private Double totalAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentMethod")
    private PaymentMethod paymentMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentStatus")
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_book", joinColumns = @JoinColumn(name = "OrderID"), inverseJoinColumns = @JoinColumn(name = "BookID"))
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "CartID")
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