package ebookstore.com.demo.employee;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    @Size(max = 20)
    private String identityCardNumber;

    @NotBlank
    @Size(min = 10, max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    private LocalDate startDate;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private Double salary;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    public Employee() {
    }

    public Employee(
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.identityCardNumber = identityCardNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.startDate = startDate;
        this.salary = salary;
    }

    public Employee(
            Long id,
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.identityCardNumber = identityCardNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.startDate = startDate;
        this.salary = salary;
    }
}
