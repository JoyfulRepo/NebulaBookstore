package ebookstore.com.demo.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Get
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // Delete
    public boolean deleteById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteByTitle(String title) {
        Optional<Book> bookOptional = bookRepository.findByTitle(title);
        if (bookOptional.isPresent()) {
            bookRepository.deleteByTitle(title);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Book updateQuantity(Long id, Integer quantity) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setQuantity(quantity);
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    public Book updatePrice(Long id, Double price) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setPrice(price);
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    public Book updateStatus(Long id, BookStatus status) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setStatus(status);
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }
}
