package ebookstore.com.demo.author;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.book.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank
    @Size(max = 500)
    @Column(name = "bio", nullable = false, length = 500)
    private String bio;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}