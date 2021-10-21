package jo.project.springboot.controller;

import jo.project.springboot.entity.Client;
import jo.project.springboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AppController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/")
    public String getIndex(Model model, HttpSession session, RedirectAttributes attr){
        return "redirect:/logowanie";
    }

    @GetMapping("/logowanie")
    public String getLoginPage(Model model, HttpSession session, RedirectAttributes attr) {

        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId != null) {
            attr.addFlashAttribute("warning", "Jesteś już zalogowany!");
            return "redirect:/products";
        }

        Client client = new Client();

        model.addAttribute("clientId", clientId);
        model.addAttribute("client", client);
        return "logowanie";
    }

    @GetMapping("/logout")
    public String getLogoutPage(Model model, HttpSession session, RedirectAttributes attr) {

        session.setAttribute("clientId", null);
        attr.addFlashAttribute("success", "Wylogowano Pomyślnie!");
        return "redirect:/logowanie";
    }

    @PostMapping("/logowanie")
    public String getLoginPage(Model model, @ModelAttribute Client client, HttpSession session, RedirectAttributes attr) {
        model.addAttribute("client", new Client());
        Optional<Client> client1 = clientService.findByUsernameAndPassword(client.getUsername(), client.getPassword());
        if (client1.isPresent()) {
            session.setAttribute("clientId", client1.get().getUserId());
            session.setAttribute("client", client1.get());
            attr.addFlashAttribute("success", "Zalogowano Pomyślnie!");
            return "redirect:/products";
        }

        attr.addFlashAttribute("error", "Zła nazwa użytkownika lub hasło!");
        return "redirect:/logowanie";

    }


}
