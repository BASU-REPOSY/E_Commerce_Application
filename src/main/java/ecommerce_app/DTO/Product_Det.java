package ecommerce_app.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "product_det")
@Entity
public class Product_Det {
    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;
    String name;
    String price;
    String image;
    String review;

    String topic;
}
