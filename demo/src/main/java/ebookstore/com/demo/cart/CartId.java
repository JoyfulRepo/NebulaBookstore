package ebookstore.com.demo.cart;

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
public class CartId implements Serializable {

    @Column(name = "CartID")
    private Integer cartId;

    @Column(name = "CustomerID")
    private Integer customerId;

    // Getters and Setters

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CartId cartId1 = (CartId) o;
        return Objects.equals(cartId, cartId1.cartId) && Objects.equals(customerId, cartId1.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, customerId);
    }
}