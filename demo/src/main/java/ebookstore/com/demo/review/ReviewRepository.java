package ebookstore.com.demo.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {

    List<Review> findByRating(Integer rating);

    List<Review> findByBookId(Integer bookId);
}
