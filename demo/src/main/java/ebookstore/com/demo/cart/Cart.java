package ebookstore.com.demo.cart;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate lastUpdated;

    public Cart() {
    }

    public Cart(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Cart(Long id, LocalDate lastUpdated) {
        this.id = id;
        this.lastUpdated = lastUpdated;
    }
}
