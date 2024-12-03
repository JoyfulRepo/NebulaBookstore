package ebookstore.com.demo.employee;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shipper extends Employee {

    @NotBlank
    private String vehicle;

    @NotBlank
    private String vehicleLicensePlate;

    public Shipper() {
        super();
    }

    public Shipper(
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary,
            String vehicle,
            String vehicleLicensePlate) {
        super(name, gender, birthDate, identityCardNumber, phoneNumber, address, startDate, salary);
        this.vehicle = vehicle;
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    public Shipper(
            Long id,
            String name,
            Gender gender,
            LocalDate birthDate,
            String identityCardNumber,
            String phoneNumber,
            String address,
            LocalDate startDate,
            Double salary,
            String vehicle,
            String vehicleLicensePlate) {
        super(id, name, gender, birthDate, identityCardNumber, phoneNumber, address, startDate, salary);
        this.vehicle = vehicle;
        this.vehicleLicensePlate = vehicleLicensePlate;
    }
}
