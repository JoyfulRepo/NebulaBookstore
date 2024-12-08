package ebookstore.com.demo.review;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ReviewId implements Serializable {

    @Column(name = "ReviewID")
    private Long reviewId;

    @Column(name = "CustomerID")
    private Long customerId;

    @Column(name = "BookID")
    private Long bookId;

    public ReviewId() {
    }

    public ReviewId(Long reviewId, Long customerId, Long bookId) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.bookId = bookId;
    }

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