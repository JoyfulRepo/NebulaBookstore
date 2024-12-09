package ebookstore.com.demo.discount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    // Add
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    // Get
    public List<Discount> findAll() {
        return (List<Discount>) discountRepository.findAll();
    }

    public Optional<Discount> findById(Integer id) {
        return discountRepository.findById(id);
    }

    // Delete
    public boolean deleteById(Integer id) {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        if (discountOptional.isPresent()) {
            discountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Update
    public Discount updateEndDate(Integer id, LocalDate endDate) {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            discount.setEndDate(endDate);
            return discountRepository.save(discount);
        } else {
            throw new RuntimeException("Discount not found with id: " + id);
        }
    }

    public Discount updateAmount(Integer id, BigDecimal amount) {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            discount.setAmount(amount);
            return discountRepository.save(discount);
        } else {
            throw new RuntimeException("Discount not found with id: " + id);
        }
    }
}
