package ListProj.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ListProj.models.PersonalModel;
import ListProj.models.loginModel;
import ListProj.services.EntryBussinessServiceInterface;
import ListProj.services.PersonalBussinessService;
import ListProj.services.PersonalBussinessServiceInterface;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    PersonalBussinessServiceInterface service;

   @Autowired
    public PersonalController() {
        super();
        //this.service = service;
    } 
    
    @GetMapping("/")
    public String displayPersonal(Model model) {
        /*
        model.addAttribute("PersonalModel", new PersonalModel("Etaoin", "Etaoin", 75));
        model.addAttribute("type", "p");
        model.addAttribute("Name", "Etaoin");
        model.addAttribute("Password", "Etaoin");
        model.addAttribute("weight", "75");
        */
        if (model.getAttribute("logged")!="true") {
            model.addAttribute("loginModel", new loginModel());
            model.addAttribute("type", "L"); //return to login screen if not logged in
            //return "layouts/defaultLayout.html";
            return "redirect:/login/";
        }
        if (model.getAttribute("PersonalModel")==null) {
                model.addAttribute("PersonalModel", new PersonalModel());
                model.addAttribute("type", "p");
        }
        PersonalModel PersonalModel = (PersonalModel) model.getAttribute("PersonalModel");
        model.addAttribute("Name", PersonalModel.getName());   
        model.addAttribute("Password", PersonalModel.getPassword());
        model.addAttribute("weight", PersonalModel.getWeight());
        model.addAttribute("type", "p");
        return "layouts/defaultLayout.html";
    }
    
    @PostMapping("/processPersonal")
    public String processPersonal(@Valid PersonalModel PersonalModel, BindingResult bindingResult, Model model) {

        PersonalBussinessService PersonalBussinessService = new PersonalBussinessService();
        PersonalBussinessService.updatePersonal(PersonalModel);
        model.addAttribute("type", "p");
        return "layouts/defaultLayout.html";
    }
}
