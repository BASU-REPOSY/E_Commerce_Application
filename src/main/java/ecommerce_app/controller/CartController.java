package ecommerce_app.controller;

import ecommerce_app.DTO.CartDTO;
import ecommerce_app.DTO.CartItemDTO;
import ecommerce_app.services.CartRedisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRedisService cartService;

    @PostMapping("/{userId}/add")
    public CartDTO addToCart(@PathVariable Long userId, @RequestBody CartItemDTO item) {
        return cartService.addItem(userId, item);
    }

    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable Long userId) {
        if(cartService.getCart(userId) == null) {
            CartDTO emptyCart = new CartDTO();
            emptyCart.setUserId(userId);
            emptyCart.setUpdatedAt("N/A");
            emptyCart.setMessage("Cart is empty");
            return emptyCart;
        }
        return cartService.getCart(userId);
    }

    @PutMapping("/{userId}/update/{productId}")
    public CartDTO updateQuantity(@PathVariable Long userId,
                                  @PathVariable Long productId,
                                  @RequestParam Integer qty) {
        return cartService.updateQuantity(userId, productId, qty);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public void removeItem(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeItem(userId, productId);
    }

    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
