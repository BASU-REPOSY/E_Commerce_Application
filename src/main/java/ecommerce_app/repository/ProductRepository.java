package ecommerce_app.repository;

import ecommerce_app.DTO.Product_Det;
import ecommerce_app.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product_Det, Long> {
    Optional<List<Product_Det>> findByName(String name);
}