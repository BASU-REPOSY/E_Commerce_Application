package ecommerce_app.controller;

import ecommerce_app.DTO.AuthResponse;
import ecommerce_app.DTO.Product_Det;
import ecommerce_app.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController("/")
@RequestMapping("/product_det")
public class Product_Controller {

    @Autowired
    ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(Product_Controller.class);

    @GetMapping()
    public ResponseEntity<List<Product_Det>> getProduct(@RequestBody Product_Det productDet){
        log.info("EXECUTED");
        Optional<List<Product_Det>> products = productService.getProducts(productDet.getName());
        List<Product_Det> prodList = products.orElse(Collections.emptyList());
        if(prodList.isEmpty()) {
            return new ResponseEntity<List<Product_Det>>( prodList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product_Det>>( prodList, HttpStatus.OK);
    }

    @PostMapping()
    public String addProduct(@RequestBody Product_Det productDet){
        productService.addNewProduct(productDet);
        return "ADDED";
    }
}
