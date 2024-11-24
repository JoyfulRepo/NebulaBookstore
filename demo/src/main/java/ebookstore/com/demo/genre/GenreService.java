package ebookstore.com.demo.genre;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    // Add
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    // Get
    public List<Genre> findAll() {
        return (List<Genre>) genreRepository.findAll();
    }

    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    // Delete
    public boolean deleteById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isPresent()) {
            genreRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Genre updateName(Long id, String name) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isPresent()) {
            Genre genre = genreOptional.get();
            genre.setName(name);
            return genreRepository.save(genre);
        } else {
            throw new RuntimeException("Genre not found with id: " + id);
        }
    }
}
