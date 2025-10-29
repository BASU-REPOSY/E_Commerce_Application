package ecommerce_app.DTO;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO implements Serializable {
    private Long userId;
    private List<CartItemDTO> items = new ArrayList<>();
    private String updatedAt ;
}
