package ebookstore.com.demo.author;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Add
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    // Get
    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    // Delete
    public boolean deleteById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            authorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Author updateBio(Long id, String bio) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setBio(bio);
            return authorRepository.save(author);
        } else {
            throw new RuntimeException("Author not found with id: " + id);
        }
    }
}
