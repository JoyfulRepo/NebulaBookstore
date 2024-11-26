package ebookstore.com.demo.actLog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActLogService {

    @Autowired
    private ActLogRepository actLogRepository;

    // Add
    public ActLog save(ActLog actLog) {
        return actLogRepository.save(actLog);
    }

    // Get
    public List<ActLog> findAll() {
        return (List<ActLog>) actLogRepository.findAll();
    }

    public Optional<ActLog> findById(Long id) {
        return actLogRepository.findById(id);
    }

    public List<ActLog> findByAction(String action) {
        return actLogRepository.findByAction(action);
    }

    // Delete
    public boolean deleteById(Long id) {
        Optional<ActLog> actLogOptional = actLogRepository.findById(id);
        if (actLogOptional.isPresent()) {
            actLogRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    // No editing
}
