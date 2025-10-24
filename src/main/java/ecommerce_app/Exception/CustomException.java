package ecommerce_app.Exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{
    String message;
    HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
