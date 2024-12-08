package ebookstore.com.demo.activityLog;

import java.time.LocalDate;
import java.time.LocalTime;

import ebookstore.com.demo.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
public class ActivityLog {

    @EmbeddedId
    private ActivityLogId id;

    @NotBlank
    @Column(name = "ActionType", nullable = false, length = 50)
    private String actionType;

    @NotBlank
    @Column(name = "ActionDescription", nullable = false, length = 255)
    private String actionDescription;

    @NotNull
    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "Time", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "CustomerID", insertable = false, updatable = false)
    private Customer customer;
}