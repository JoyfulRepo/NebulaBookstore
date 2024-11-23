package ebookstore.com.demo.author;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findByName(String name);

    void deleteByName(String name);
}
