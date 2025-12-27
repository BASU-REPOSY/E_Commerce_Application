package ecommerce_app.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_det")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.STRING)
public abstract class ProductDet {
    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "name")
    private String name;

    // map discriminator column to a read-only field if you want to read the category
    @Column(name = "category", insertable = false, updatable = false)
    private String category;
}
