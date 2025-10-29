package ecommerce_app.services;

import ecommerce_app.DTO.CartDTO;
import ecommerce_app.DTO.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CART_KEY_PREFIX = "CART:";

    private String getCartKey(Long userId) {
        return CART_KEY_PREFIX + userId;
    }

    public CartDTO getCart(Long userId) {
        return (CartDTO) redisTemplate.opsForValue().get(getCartKey(userId));
    }

    public CartDTO addItem(Long userId, CartItemDTO item) {
        CartDTO cart = getCart(userId);
        if (cart == null) {
            cart = new CartDTO();
            cart.setUserId(userId);
        }

        // Check if product already exists in cart
        Optional<CartItemDTO> existing = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + item.getQuantity());
        } else {
            cart.getItems().add(item);
        }

        cart.setUpdatedAt("GA");

        redisTemplate.opsForValue().set(getCartKey(userId), cart, Duration.ofHours(6));
        return cart;
    }

    public CartDTO updateQuantity(Long userId, Long productId, Integer newQty) {
        CartDTO cart = getCart(userId);
        if (cart == null) throw new RuntimeException("Cart not found");

        cart.getItems().forEach(item -> {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(newQty);
            }
        });

        redisTemplate.opsForValue().set(getCartKey(userId), cart, Duration.ofHours(6));
        return cart;
    }

    public void removeItem(Long userId, Long productId) {
        CartDTO cart = getCart(userId);
        if (cart != null) {
            cart.getItems().removeIf(item -> item.getProductId().equals(productId));
            redisTemplate.opsForValue().set(getCartKey(userId), cart, Duration.ofHours(6));
        }
    }

    public void clearCart(Long userId) {
        redisTemplate.delete(getCartKey(userId));
    }
}
