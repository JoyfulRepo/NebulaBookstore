package ebookstore.com.demo.discount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountService.findAll();
    }

    // Add
    @PostMapping
    public Discount addDiscount(@RequestBody Discount discount) {
        return discountService.save(discount);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountById(@PathVariable Integer id) {
        boolean isDeleted = discountService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable Integer id) {
        return discountService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update
    @PutMapping("/{id}/end-date")
    public ResponseEntity<Discount> updateDiscountEndDate(@PathVariable Integer id, @RequestParam String endDate) {
        try {
            LocalDate parsedDate = LocalDate.parse(endDate);
            Discount updatedDiscount = discountService.updateEndDate(id, parsedDate);
            return ResponseEntity.ok(updatedDiscount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}/amount")
    public ResponseEntity<Discount> updateDiscountAmount(@PathVariable Integer id, @RequestParam BigDecimal amount) {
        try {
            Discount updatedDiscount = discountService.updateAmount(id, amount);
            return ResponseEntity.ok(updatedDiscount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
