package jo.project.springboot.service;

import jo.project.springboot.entity.Client;
import jo.project.springboot.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> findByUsernameAndPassword(String username, String password) {
        return clientRepository.findByUsernameAndPassword(username, password);
    }
}
