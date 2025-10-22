package ecommerce_app.controller;

import ecommerce_app.DTO.User;
import ecommerce_app.services.UserService;
import ecommerce_app.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }
    @PostMapping("/login")
    public Map<String, String> authenticateUser(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String jwt = jwtUtils.generateJwtToken(authentication.getName());
        return Map.of("token", jwt);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }
}
