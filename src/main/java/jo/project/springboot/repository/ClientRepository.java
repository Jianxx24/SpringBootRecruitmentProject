package jo.project.springboot.repository;

import jo.project.springboot.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByUsernameAndPassword(String username, String password);
}
