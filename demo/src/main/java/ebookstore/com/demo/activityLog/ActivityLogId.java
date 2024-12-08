package ebookstore.com.demo.activityLog;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ActivityLogId implements Serializable {

    @Column(name = "LogID")
    private Long logId;

    @Column(name = "CustomerID")
    private Long customerId;

    public ActivityLogId() {
    }

    public ActivityLogId(Long logId, Long customerId) {
        this.logId = logId;
        this.customerId = customerId;
    }

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