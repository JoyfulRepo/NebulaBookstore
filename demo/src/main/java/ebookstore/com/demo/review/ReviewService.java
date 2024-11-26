package ebookstore.com.demo.review;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Add
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    // Get
    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findByRating(Integer rating) {
        return reviewRepository.findByRating(rating);
    }

    // Delete
    public boolean deleteById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            reviewRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    // No editing a review's rating or comment
}
