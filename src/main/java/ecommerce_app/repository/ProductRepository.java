package ecommerce_app.repository;

import ecommerce_app.DTO.ProductDet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductDet, String> {
    Optional<List<ProductDet>> findByName(String name);
}