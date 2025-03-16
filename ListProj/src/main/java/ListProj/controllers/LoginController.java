package ListProj.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ListProj.data.PersonalClientService;
import ListProj.models.EntryModel;
import ListProj.models.PersonalModel;
import ListProj.models.loginModel;
import ListProj.services.PersonalBussinessService;
import ListProj.services.PersonalBussinessServiceInterface;

//
@Controller
@RequestMapping("/login")
public class LoginController {

    PersonalBussinessServiceInterface service;

   @Autowired
    public LoginController() {
        super();
        //this.service = service;
    } 

    @GetMapping("/")
    public String displayLoginFrom(Model model) {

        model.addAttribute("loginModel", new loginModel());
        model.addAttribute("type", "L");

        return "layouts/defaultLayout.html";
    }

    @PostMapping("/processLogin")
    public String processLogin(@Valid loginModel loginModel, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) { //goes back to the login form if there are errors
            model.addAttribute("loginModel", loginModel);
            model.addAttribute("type", "L");
            return "layouts/defaultLayout.html";
        }
        PersonalBussinessService PersonalBussinessService = new PersonalBussinessService();
        String name=loginModel.getUsername();
        String password=loginModel.getPassword();
        if (PersonalBussinessService.getPersonal(name).isPresent()) { //registers new User if not in the database
            PersonalModel PersonalModel = new PersonalModel(name, password, 0);
            PersonalBussinessService.addPersonal(PersonalModel);
            model.addAttribute("PersonalModel", PersonalModel);
            model.addAttribute("logged", true);
            model.addAttribute("type", "p"); // go to Personal Data page
            return "redirect:/personal/";
        }
        if (PersonalBussinessService.checkPassword(name, password)==false) { //goes back to the login form if there are errors
            model.addAttribute("loginModel", loginModel);
            model.addAttribute("type", "L");
            return "layouts/defaultLayout.html";
        }
        PersonalModel PersonalModel = PersonalBussinessService.getPersonal(name).get();
        model.addAttribute("PersonalModel", PersonalModel);
        //model.addAttribute("loginModel", loginModel);
        model.addAttribute("logged", true);
        model.addAttribute("type", "p"); // go to Personal Data page
        return "redirect:/personal/";
    }

    /*
    @GetMapping("/List")
    public String displayList(Model model) {

        model.addAttribute("EntryModel", model);
        model.addAttribute("type", "E");
        for (int i = 0; i < 54; i++) {
            model.addAttribute("item"+String.valueOf(i), false);
            //System.out.println("item"+String.valueOf(i));
        }
        return "layouts/defaultLayout.html";
    }

    @GetMapping("/test2")
    @ResponseBody
    public String printHelloWorld() {
        return "this is still working";
    }
        */

}
