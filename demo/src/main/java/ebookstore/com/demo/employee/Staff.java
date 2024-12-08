package ebookstore.com.demo.employee;

import ebookstore.com.demo.storage.Storage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
public class Staff extends Employee {

    @NotBlank
    @Size(max = 50)
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @NotBlank
    @Size(max = 255)
    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    @ManyToOne
    @JoinColumn(name = "StorageID")
    private Storage storage;
}