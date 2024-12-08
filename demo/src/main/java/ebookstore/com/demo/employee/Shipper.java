package ebookstore.com.demo.employee;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Shipper extends Employee {

    @NotBlank
    @Column(name = "VehicleNumber", nullable = false, length = 20)
    private String vehicleNumber;

    @NotBlank
    @Column(name = "VehicleType", nullable = false, length = 30)
    private String vehicleType;
}
