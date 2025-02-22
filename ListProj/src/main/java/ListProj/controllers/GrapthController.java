package ListProj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Graph")
public class GrapthController {
    
    @GetMapping("/")
    public String displayHome(Model model) {
        model.addAttribute("type", "G");
        return "layouts/defaultLayout.html";
    }

}
