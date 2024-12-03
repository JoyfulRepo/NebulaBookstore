package ebookstore.com.demo.employee;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Manager extends Employee {

    @NotNull
    private Integer performanceScore;

    public Manager() {
        super();
    }

    public Manager(
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary,
            Integer performanceScore) {
        super(name, gender, birthDate, identityCardNumber, phoneNumber, address, startDate, salary);
        this.performanceScore = performanceScore;
    }

    public Manager(
            Long id,
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary,
            Integer performanceScore) {
        super(id, name, gender, birthDate, identityCardNumber, phoneNumber, address, startDate, salary);
        this.performanceScore = performanceScore;
    }
}
