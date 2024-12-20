package ebookstore.com.demo.review;

import ebookstore.com.demo.book.Book;
import ebookstore.com.demo.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
public class Review {

    @EmbeddedId
    private ReviewId id;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "Review", nullable = false, length = 1000)
    private String review;

    @ManyToOne
    @JoinColumn(name = "CustomerID", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "BookID", insertable = false, updatable = false)
    private Book book;
}