package jo.project.springboot.controller;

import jo.project.springboot.entity.Client;
import jo.project.springboot.entity.Product;
import jo.project.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    public Product saveProduct(Product product) {
        return productService.saveProduct(product);
    }

    // DISPLAYING ALL PRODUCT ENTRIES

    @GetMapping("/products")
    public String getProducts(HttpSession session, Model model, RedirectAttributes attr) {
        System.out.println(session.getAttribute("clientId"));
        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId != null) {
            List<Product> products = productService.findAll();
            model.addAttribute("products", products);
            return "products";
        }
        attr.addFlashAttribute("error", "Należy się wpierw zalogować!");
        return "redirect:/logowanie";
    }

//    ----------------------------------------------------

//    MODIFYING A PRODUCT

    @PostMapping("/products/getmod")
    public String getProductById(@RequestParam String productId, HttpSession session, Model model, RedirectAttributes attr) {
        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId != null) {
            Product product = productService.findProductById(Long.parseLong(productId)).get();
            model.addAttribute("product", product);

            return "product_modify";
        }
        attr.addFlashAttribute("error", "Należy się wpierw zalogować!");
        return "redirect:/logowanie";
    }

    @PostMapping("/products/modify")
    public String setProductById(@ModelAttribute Product product, HttpSession session, Model model, RedirectAttributes attr) {
        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId != null) {

            productService.modifyProductById(product);

            attr.addFlashAttribute("success", "Produkt zmodyfikowany pomyślnie!");
            return "redirect:/products";
        }
        attr.addFlashAttribute("error", "Należy się wpierw zalogować!");
        return "redirect:/logowanie";
        }
//    ----------------------------------------------------

//    REMOVING A PRODUCT

    @PostMapping("/products/remove")
    public String deleteProductById(@RequestParam String productId, HttpSession session, Model model, RedirectAttributes attr) {
        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId != null) {

            productService.deleteProductById(Long.parseLong(productId));
            attr.addFlashAttribute("success", "Produkt usunięty pomyślnie!");
            return "redirect:/products";
        }
        attr.addFlashAttribute("error", "Należy się wpierw zalogować!");
        return "redirect:/logowanie";
    }

//    ----------------------------------------------------

//    ADDING A PRODUCT

    @GetMapping("/products/add")
    public String getAddProduct(HttpSession session, Model model, RedirectAttributes attr) {
        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId != null) {
            Product product = new Product();

            model.addAttribute("product", product);


            return "product";
        }
        attr.addFlashAttribute("error", "Należy się wpierw zalogować!");
        return "redirect:/logowanie";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product, HttpSession session, Model model, RedirectAttributes attr) {
        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId != null) {

            productService.saveProduct(product);

            attr.addFlashAttribute("success", "Produkt dodany pomyślnie!");
            return "redirect:/products";
        }
        attr.addFlashAttribute("error", "Należy się wpierw zalogować!");
        return "redirect:/logowanie";
    }

//    -----------------------------------------------------

    public Optional<Product> findProductById(long productId) {
        return productService.findProductById(productId);
    }

    public Optional<Product> findProductByName(String productName) {
        return productService.findProductByName(productName);
    }

    public void deleteProductById(long productId) {
        productService.deleteProductById(productId);
    }


}
