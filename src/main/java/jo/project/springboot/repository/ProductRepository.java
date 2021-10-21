package jo.project.springboot.repository;

import jo.project.springboot.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

    List<Product> findAllByOrderByProductId();
}
