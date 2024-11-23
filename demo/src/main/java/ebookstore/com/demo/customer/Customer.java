package ebookstore.com.demo.customer;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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

    public Customer() {
    }

    public Customer(
            String name,
            CustomerGender gender,
            String email,
            LocalDate joinDate,
            String phoneNumber,
            String username,
            String password) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.joinDate = joinDate;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public Customer(
            Long id,
            String name,
            CustomerGender gender,
            String email,
            LocalDate joinDate,
            String phoneNumber,
            String username,
            String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.joinDate = joinDate;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerGender getGender() {
        return gender;
    }

    public void setGender(CustomerGender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
