package com.smart.smartApi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductPromotionId implements Serializable {
    @Serial
    private static final long serialVersionUID = -3002950642697809785L;
    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @NotNull
    @Column(name = "promotion_id", nullable = false)
    private Integer promotionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductPromotionId entity = (ProductPromotionId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.promotionId, entity.promotionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, promotionId);
    }

}