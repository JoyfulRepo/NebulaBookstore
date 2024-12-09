package ebookstore.com.demo.publisher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    // Add
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    // Get
    public List<Publisher> findAll() {
        return (List<Publisher>) publisherRepository.findAll();
    }

    public Optional<Publisher> findById(Integer id) {
        return publisherRepository.findById(id);
    }

    public List<Publisher> findByName(String name) {
        return publisherRepository.findByName(name);
    }

    // Delete
    public boolean deleteById(Integer id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()) {
            publisherRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Publisher updateAddress(Integer id, String address) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            publisher.setAddress(address);
            return publisherRepository.save(publisher);
        } else {
            throw new RuntimeException("Publisher not found with id: " + id);
        }
    }

    public Publisher updatePhoneNumber(Integer id, String phoneNumber) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            publisher.setPhoneNumber(phoneNumber);
            return publisherRepository.save(publisher);
        } else {
            throw new RuntimeException("Publisher not found with id: " + id);
        }
    }
}
