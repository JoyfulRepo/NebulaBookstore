package ebookstore.com.demo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebookstore.com.demo.book.Book.BookStatus;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    // Add
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        boolean isDeleted = bookService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title) {
        List<Book> books = bookService.findByTitle(title);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(books);
    }

    // Update
    @PutMapping("/{id}/quantity")
    public ResponseEntity<Book> updateBookQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        try {
            Book updatedBook = bookService.updateQuantity(id, quantity);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Book> updateBookPrice(@PathVariable Long id, @RequestParam Double price) {
        try {
            Book updatedBook = bookService.updatePrice(id, price);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Book> updateBookStatus(@PathVariable Long id, @RequestParam BookStatus status) {
        try {
            Book updatedBook = bookService.updateStatus(id, status);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Associate Author
    @PutMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<Book> addAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        try {
            Book updatedBook = bookService.addAuthor(bookId, authorId);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Associate Genre
    @PutMapping("/{bookId}/genres/{genreId}")
    public ResponseEntity<Book> addGenreToBook(@PathVariable Long bookId, @PathVariable Long genreId) {
        try {
            Book updatedBook = bookService.addGenre(bookId, genreId);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Associate Publisher
    @PutMapping("/{bookId}/publisher/{publisherId}")
    public ResponseEntity<Book> setPublisherForBook(@PathVariable Long bookId, @PathVariable Long publisherId) {
        try {
            Book updatedBook = bookService.setPublisher(bookId, publisherId);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
