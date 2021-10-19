package jo.project.springboot.controller;

import jo.project.springboot.entity.Product;
import jo.project.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(){
        return "This is a placeholder";
    }
    public Product saveProduct(Product product){
        return productService.saveProduct(product);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
    public Optional<Product> findProductById(long productId){
        return productService.findProductById(productId);
    }
    public Optional<Product> findProductByName(String productName){
        return productService.findProductByName(productName);
    }
    public void deleteProductById(long productId){
        productService.deleteProductById(productId);
    }

    public Product modifyProduct(Product product){
        return productService.modifyProductById(product);
    }

}
