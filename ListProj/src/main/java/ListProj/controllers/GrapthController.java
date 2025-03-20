package ListProj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ListProj.models.loginModel;

@Controller
@RequestMapping("/Graph")
public class GrapthController {
    
    @GetMapping("/")
    public String displayHome(Model model) {

        if (model.getAttribute("logged")!="true") {
            model.addAttribute("loginModel", new loginModel());
            model.addAttribute("type", "L"); //return to login screen if not logged in
            return "redirect:/login/";
        }

        model.addAttribute("type", "G");
        return "layouts/defaultLayout.html";
    }

}
