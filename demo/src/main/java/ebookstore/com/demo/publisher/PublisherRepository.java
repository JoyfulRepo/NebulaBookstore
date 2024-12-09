package ebookstore.com.demo.publisher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    List<Publisher> findByName(String name);
}
