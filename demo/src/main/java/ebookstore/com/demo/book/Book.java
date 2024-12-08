package ebookstore.com.demo.book;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.author.Author;
import ebookstore.com.demo.genre.Genre;
import ebookstore.com.demo.publisher.Publisher;
import ebookstore.com.demo.review.Review;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "Title", nullable = false)
    private String title;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    @Column(name = "Price", nullable = false)
    private Double price;

    @Size(max = 500)
    @Column(name = "CoverImage", nullable = true)
    private String coverImage;

    @NotNull
    @Min(0)
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "PublicationDate", nullable = true)
    private LocalDate publicationDate;

    @Size(max = 1000)
    @Column(name = "BriefDescription", nullable = true)
    private String briefDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "PublisherID", nullable = true)
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(name = "BOOK_HAS_GENRE", joinColumns = @JoinColumn(name = "BookID"), inverseJoinColumns = @JoinColumn(name = "GenreID"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "BOOK_HAS_AUTHOR", joinColumns = @JoinColumn(name = "BookID"), inverseJoinColumns = @JoinColumn(name = "AuthorID"))
    private List<Author> authors;

    public enum BookStatus {
        Upcoming,
        In_Stock,
        Out_Of_Stock
    }
}