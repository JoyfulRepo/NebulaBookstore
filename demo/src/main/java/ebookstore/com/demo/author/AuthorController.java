package ebookstore.com.demo.author;

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

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    // Add
    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Integer id) {
        boolean isDeleted = authorService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        return authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Author>> getAuthorByName(@PathVariable String name) {
        List<Author> authors = authorService.findByName(name);
        if (authors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(authors);
    }

    // Update
    @PutMapping("/{id}/bio")
    public ResponseEntity<Author> updateAuthorBio(@PathVariable Integer id, @RequestParam String bio) {
        try {
            Author author = authorService.updateBio(id, bio);
            return ResponseEntity.ok(author);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
