package ebookstore.com.demo.storage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    // Add
    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }

    // Get
    public List<Storage> findAll() {
        return (List<Storage>) storageRepository.findAll();
    }

    public Optional<Storage> findById(Integer id) {
        return storageRepository.findById(id);
    }

    // Delete
    public boolean deleteById(Integer id) {
        Optional<Storage> storageOptional = storageRepository.findById(id);
        if (storageOptional.isPresent()) {
            storageRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Storage updateCapacity(Integer id, Integer capacity) {
        Optional<Storage> storageOptional = storageRepository.findById(id);
        if (storageOptional.isPresent()) {
            Storage storage = storageOptional.get();
            storage.setCapacity(capacity);
            return storageRepository.save(storage);
        } else {
            throw new RuntimeException("Storage not found with id: " + id);
        }
    }
}
