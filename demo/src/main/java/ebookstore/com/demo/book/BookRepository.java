package ebookstore.com.demo.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :authorName")
    List<Book> findByAuthorName(@Param("authorName") String authorName);

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genreName")
    List<Book> findByGenreName(@Param("genreName") String genreName);
}
