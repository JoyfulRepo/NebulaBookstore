package ebookstore.com.demo.customer;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.cart.Cart;
import ebookstore.com.demo.order.Order;
import ebookstore.com.demo.review.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    private CustomerGender gender;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    private LocalDate joinDate;

    @NotBlank
    @Size(min = 10, max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    public enum CustomerGender {
        MALE,
        FEMALE,
        OTHER
    }
}