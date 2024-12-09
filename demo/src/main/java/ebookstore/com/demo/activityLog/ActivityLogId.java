package ebookstore.com.demo.activityLog;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ActivityLogId implements Serializable {

    @Column(name = "LogID")
    private Long logId;

    @Column(name = "CustomerID")
    private Long customerId;

    // Getters and Setters

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ActivityLogId actLogId = (ActivityLogId) o;
        return Objects.equals(logId, actLogId.logId) &&
                Objects.equals(customerId, actLogId.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, customerId);
    }
}