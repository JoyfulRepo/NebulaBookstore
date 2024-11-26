package ebookstore.com.demo.actLog;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ActLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String action;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate actDate;

    @NotNull
    private LocalTime actTime;

    public ActLog() {
    }

    public ActLog(
            String action,
            String description,
            LocalDate actDate,
            LocalTime actTime) {
        this.action = action;
        this.description = description;
        this.actDate = actDate;
        this.actTime = actTime;
    }

    public ActLog(
            Long id,
            String action,
            String description,
            LocalDate actDate,
            LocalTime actTime) {
        this.id = id;
        this.action = action;
        this.description = description;
        this.actDate = actDate;
        this.actTime = actTime;
    }
}
