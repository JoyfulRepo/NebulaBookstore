package ebookstore.com.demo.review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    @NotBlank
    @Size(max = 500)
    private String comment;

    public Review() {
    }

    public Review(Integer rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Review(Long id, Integer rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }
}
