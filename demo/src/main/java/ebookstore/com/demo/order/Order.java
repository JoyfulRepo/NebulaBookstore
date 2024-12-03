package ebookstore.com.demo.order;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.book.Book;
import ebookstore.com.demo.customer.Customer;
import ebookstore.com.demo.discount.Discount;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_book", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

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
