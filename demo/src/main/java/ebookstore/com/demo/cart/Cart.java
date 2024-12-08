package ebookstore.com.demo.cart;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.book.Book;
import ebookstore.com.demo.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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
@Table(name = "CART")
public class Cart {

    @EmbeddedId
    private CartId id;

    @Column(name = "LastModified", nullable = true)
    private LocalDate lastUpdated;

    @ManyToMany
    @JoinTable(name = "CART_CONTAIN_BOOK", joinColumns = {
            @JoinColumn(name = "CartID", referencedColumnName = "CartID"),
            @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID") // Correct reference
    }, inverseJoinColumns = @JoinColumn(name = "BookID"))
    private List<Book> books;

    @ManyToOne
    @MapsId("customerId") // Specifies which part of the composite key this maps to
    @JoinColumn(name = "CustomerID", nullable = false) // Remove `insertable=false, updatable=false`
    private Customer customer;
}