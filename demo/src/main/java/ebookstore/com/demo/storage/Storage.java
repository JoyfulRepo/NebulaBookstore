package ebookstore.com.demo.storage;

import ebookstore.com.demo.employee.Manager;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
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
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StorageID")
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "StorageName", nullable = false, length = 100)
    private String name;

    @NotBlank
    @Size(max = 200)
    @Column(name = "Address", nullable = false, length = 200)
    private String address;

    @NotNull
    @Min(1)
    @Column(name = "Capacity", nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "ManagerID")
    private Manager manager;
}