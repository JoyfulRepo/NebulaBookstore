package ebookstore.com.demo.employee;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff findById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }

    public List<String> getWorkSchedule(Long staffId) {
        return Arrays.asList(
                "Monday: 9 AM - 5 PM",
                "Tuesday: 9 AM - 5 PM",
                "Wednesday: 9 AM - 5 PM",
                "Thursday: 9 AM - 5 PM",
                "Friday: 9 AM - 5 PM",
                "Saturaday: 9 AM - 12 PM");
    }
}