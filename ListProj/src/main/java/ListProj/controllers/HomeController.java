package ListProj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController {
    
    @GetMapping("/")
    public String displayHome(Model model) {
        model.addAttribute("type", "H");
        return "layouts/defaultLayout.html";
    }

    @GetMapping("/about")
    public String displayAbout(Model model) {
        model.addAttribute("type", "A");
        return "layouts/defaultLayout.html";
    }

    @GetMapping("/contact")
    public String displayContact(Model model) {
        model.addAttribute("type", "C");
        return "layouts/defaultLayout.html";
    }

    @GetMapping("/terms")
    public String displayTerms(Model model) {
        model.addAttribute("type", "T");
        return "layouts/defaultLayout.html";
    }

    @GetMapping("/privacy")
    public String displayPrivacy(Model model) {
        model.addAttribute("type", "P");
        return "layouts/defaultLayout.html";
    }
}
