package ecommerce_app.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
}
