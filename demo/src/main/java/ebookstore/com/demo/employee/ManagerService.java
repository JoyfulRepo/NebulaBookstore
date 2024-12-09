package ebookstore.com.demo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    public Manager findById(Long id) {
        return managerRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }
}