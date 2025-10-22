package ecommerce_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Product_Controller {

    private static final Logger log = LoggerFactory.getLogger(Product_Controller.class);

    @GetMapping
    public String getProduct(){
        log.info("EXECUTED");
        return "WELCOME";
    }
}
