package ebookstore.com.demo.review;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReviewId implements Serializable {

    @Column(name = "ReviewID")
    private Integer reviewId;

    @Column(name = "CustomerID")
    private Integer customerId;

    @Column(name = "BookID")
    private Integer bookId;

    // Getters and Setters

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ReviewId reviewId1 = (ReviewId) o;
        return Objects.equals(reviewId, reviewId1.reviewId) &&
                Objects.equals(customerId, reviewId1.customerId) &&
                Objects.equals(bookId, reviewId1.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, customerId, bookId);
    }
}