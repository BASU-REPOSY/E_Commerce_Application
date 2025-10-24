package ecommerce_app.DTO;

import lombok.Data;

@Data
public class AuthResponse {
    String token;
    Error error;
}
