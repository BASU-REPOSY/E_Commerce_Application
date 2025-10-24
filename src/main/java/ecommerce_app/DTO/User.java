package ecommerce_app.DTO;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

//@Data
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
}
