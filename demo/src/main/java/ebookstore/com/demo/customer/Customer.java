package ebookstore.com.demo.customer;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.cart.Cart;
import ebookstore.com.demo.order.Order;
import ebookstore.com.demo.review.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @Column(name = "CustomerID")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Size(max = 255)
    @Column(name = "PasswordHash", nullable = false)
    private String password;

    @NotBlank
    @Size(max = 100)
    @Column(name = "CustomerName", nullable = false)
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private CustomerGender gender;

    @Size(max = 20)
    @Column(name = "PhoneNumber", nullable = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "JoinDate", nullable = false)
    private LocalDate joinDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    public enum CustomerGender {
        Male,
        Female,
        Other
    }
}