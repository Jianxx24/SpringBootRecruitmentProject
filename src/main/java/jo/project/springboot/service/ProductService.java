package jo.project.springboot.service;

import jo.project.springboot.entity.Product;
import jo.project.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(long productId) {
        return productRepository.findById(productId);
    }

    public Optional<Product> findProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public void deleteProductById(long productId) {
        productRepository.deleteById(productId);
    }

    public Product modifyProductById(Product product) {
        Product modifiedProduct = productRepository.findById(product.getProductId()).get();
        modifiedProduct.setProductName(product.getProductName());
        modifiedProduct.setPrice(product.getPrice());
        modifiedProduct.setQuantity(product.getQuantity());
        modifiedProduct.setComment(product.getComment());
        System.out.println("Produkt: " + modifiedProduct);
        return productRepository.save(modifiedProduct);
    }

    public List<Product> findAll() {
        return productRepository.findAllByOrderByProductId();
    }
}
