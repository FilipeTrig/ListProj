package ListProj.controllers;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ListProj.models.PersonalModel;
import ListProj.models.loginModel;

@Controller
@RequestMapping("/personal")
public class PersonalController {
    
    @GetMapping("/")
    public String displayPersonal(Model model) {

        model.addAttribute("PersonalModel", new PersonalModel("Etaoin", "Etaoin", 75));
        model.addAttribute("type", "p");
        model.addAttribute("Name", "Etaoin");
        model.addAttribute("Password", "Etaoin");
        model.addAttribute("weight", "75");

        return "layouts/defaultLayout.html";
    }
    
    @PostMapping("/processPersonal")
    public String processPersonal(@Valid PersonalModel PersonalModel, BindingResult bindingResult, Model model) {

        model.addAttribute("PersonalModel", new PersonalModel("Etaoin", "Etaoin", 75));
        model.addAttribute("type", "p");
        model.addAttribute("Name", "Etaoin");
        model.addAttribute("Password", "Etaoin");
        model.addAttribute("weight", "75");

        return "layouts/defaultLayout.html";
    }
}
