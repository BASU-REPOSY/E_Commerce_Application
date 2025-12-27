package ecommerce_app.controller;


import ecommerce_app.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/review")
@Slf4j
public class ReviewController {

    @Autowired
    ProductService productService;

    @GetMapping("/ai-review/{productId}")
    public void getReview(@RequestParam String productId){
        log.info("Getting review from AI for particular product");
        productService.getProducts("1");
    }


}
