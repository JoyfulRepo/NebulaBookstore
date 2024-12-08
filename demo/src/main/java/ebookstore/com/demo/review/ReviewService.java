package ebookstore.com.demo.review;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebookstore.com.demo.book.Book;
import ebookstore.com.demo.book.BookRepository;
import ebookstore.com.demo.customer.Customer;
import ebookstore.com.demo.customer.CustomerRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Add
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public Review addReview(Long bookId, Long customerId, Integer rating, String comment) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        ReviewId reviewId = new ReviewId(null, customerId, bookId);
        Review review = new Review(reviewId, rating, comment, customer, book);
        return reviewRepository.save(review);
    }

    // Get
    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    public Optional<Review> findById(ReviewId id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findByRating(Integer rating) {
        return reviewRepository.findByRating(rating);
    }

    public List<Review> findByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    // Delete
    public boolean deleteById(ReviewId id) {
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
