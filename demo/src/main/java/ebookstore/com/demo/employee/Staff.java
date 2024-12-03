package ebookstore.com.demo.employee;

import java.time.LocalDate;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Staff extends Employee {

    public Staff() {
        super();
    }

    public Staff(
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary) {
        super(name, gender, birthDate, identityCardNumber, phoneNumber, address, startDate, salary);
    }

    public Staff(
            Long id,
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary) {
        super(id, name, gender, birthDate, identityCardNumber, phoneNumber, address, startDate, salary);
    }
}
