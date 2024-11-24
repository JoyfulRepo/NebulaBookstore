package ebookstore.com.demo.book;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private Double price;

    @NotNull
    private BookStatus status;

    @NotNull
    private Integer quantity;

    @NotNull
    private LocalDate publicationDate;

    @NotBlank
    @Size(max = 255)
    private String coverImage; // cover image URL

    @NotBlank
    @Size(max = 500)
    private String briefDescription;

    public Book() {
    }

    public Book(
            String title,
            Double price,
            BookStatus status,
            Integer quantity,
            LocalDate publicationDate,
            String coverImage,
            String briefDescription) {
        this.title = title;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
        this.publicationDate = publicationDate;
        this.coverImage = coverImage;
        this.briefDescription = briefDescription;
    }

    public Book(
            Long id,
            String title,
            Double price,
            BookStatus status,
            Integer quantity,
            LocalDate publicationDate,
            String coverImage,
            String briefDescription) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
        this.publicationDate = publicationDate;
        this.coverImage = coverImage;
        this.briefDescription = briefDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public enum BookStatus {
        AVAILABLE,
        OUT_OF_STOCK,
        DISCONTINUED,
        UPCOMING
    }
}