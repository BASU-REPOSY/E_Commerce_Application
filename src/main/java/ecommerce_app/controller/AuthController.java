package ecommerce_app.controller;

import ecommerce_app.DTO.Error;
import ecommerce_app.DTO.User;
import ecommerce_app.Exception.CustomException;
import ecommerce_app.DTO.AuthResponse;
import ecommerce_app.services.UserService;
import ecommerce_app.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody Map<String, String> payload) throws CustomException {
        String username = payload.get("username");
        String password = payload.get("password");
        AuthResponse response = new AuthResponse();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            response.setToken(jwtUtils.generateJwtToken(authentication.getName()));
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode("401");
            error.setMessage("Kindly pass the correct Credential");
            response.setError(error);
            return new ResponseEntity<AuthResponse>( response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<AuthResponse>( response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }
}
