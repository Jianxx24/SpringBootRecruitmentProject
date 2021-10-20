package jo.project.springboot.controller;

import jo.project.springboot.entity.Client;
import jo.project.springboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AppController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/logowanie")
    public String getLoginPage(Model model, HttpSession session){
        Long clientId = (Long) session.getAttribute("clientId");

        Client client = new Client();

        model.addAttribute("clientId", clientId);
        model.addAttribute("client", client);
        return "logowanie";
    }

    @GetMapping("/logout")
    public String getLogoutPage(Model model, HttpSession session){

        session.setAttribute("clientId", null);

        return "redirect:/logowanie";
    }

    @PostMapping("/logowanie")
    public String getLoginPage(Model model, @ModelAttribute Client client, HttpSession session){
        model.addAttribute("client", new Client());
        Optional<Client> client1 = clientService.findByUsernameAndPassword(client.getUsername(), client.getPassword());
        if(client1.isPresent()){
            session.setAttribute("clientId", client1.get().getUserId());
            System.out.println(session.getAttribute("clientId"));
            return "redirect:/products";
        }

        return "redirect:/logowanie";

    }



}
