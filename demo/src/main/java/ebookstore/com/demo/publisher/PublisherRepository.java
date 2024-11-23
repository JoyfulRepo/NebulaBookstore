package ebookstore.com.demo.publisher;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {

    Optional<Publisher> findByName(String name);

    void deleteByName(String name);
}
