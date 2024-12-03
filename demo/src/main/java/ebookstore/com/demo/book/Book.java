package ebookstore.com.demo.book;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.author.Author;
import ebookstore.com.demo.genre.Genre;
import ebookstore.com.demo.order.Order;
import ebookstore.com.demo.publisher.Publisher;
import ebookstore.com.demo.review.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private Double price;

    @NotNull
    private BookStatus status;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    private LocalDate publicationDate;

    @NotBlank
    @Size(max = 255)
    private String coverImage; // cover image URL

    @NotBlank
    @Size(max = 500)
    private String briefDescription;

    @ManyToMany(mappedBy = "books")
    private List<Order> orders;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public enum BookStatus {
        AVAILABLE,
        OUT_OF_STOCK,
        DISCONTINUED,
        UPCOMING
    }
}