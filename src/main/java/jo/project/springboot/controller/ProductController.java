package jo.project.springboot.controller;

import jo.project.springboot.entity.Product;
import jo.project.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.List;

@Controller
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

    @GetMapping("/products")
    public String getProducts(HttpSession session, Model model){
        System.out.println(session.getAttribute("clientId"));
        Long clientId = (Long)session.getAttribute("clientId");
        if(clientId != null){
            System.out.println("Sesja!");
            List<Product> products = productService.findAll();
            model.addAttribute("products", products);
            return "products";
        }
       return "redirect:/logowanie";
    }

    @GetMapping("/products/{id}")
    public String getProductById(HttpSession session, Model model,@PathVariable long id){
        System.out.println(session.getAttribute("clientId"));
        Long clientId = (Long)session.getAttribute("clientId");
        if(clientId != null){
            System.out.println("Sesja!");
            Product product = productService.findProductById(id).get();
            model.addAttribute("products", product);
            return "product";
        }
        return "redirect:/logowanie";
    }

    @PostMapping("/products/{id}")
    public String setProductById(HttpSession session, Model model){
        System.out.println(session.getAttribute("clientId"));
        Long clientId = (Long)session.getAttribute("clientId");
        if(clientId != null){
            System.out.println("Sesja!");

//            model.addAttribute("products", product);
            return "redirect:/products";
        }
        return "redirect:/logowanie";
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
