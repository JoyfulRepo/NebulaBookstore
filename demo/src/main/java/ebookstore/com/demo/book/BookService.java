package ebookstore.com.demo.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebookstore.com.demo.author.Author;
import ebookstore.com.demo.author.AuthorRepository;
import ebookstore.com.demo.book.Book.BookStatus;
import ebookstore.com.demo.genre.Genre;
import ebookstore.com.demo.genre.GenreRepository;
import ebookstore.com.demo.publisher.Publisher;
import ebookstore.com.demo.publisher.PublisherRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Add
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Associate Author
    public Book addAuthor(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
        book.getAuthors().add(author);
        return bookRepository.save(book);
    }

    // Associate Genre
    public Book addGenre(Long bookId, Long genreId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + genreId));
        book.getGenres().add(genre);
        return bookRepository.save(book);
    }

    // Associate Publisher
    public Book setPublisher(Long bookId, Long publisherId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + publisherId));
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    // Get
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByTitle(String title) {
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
