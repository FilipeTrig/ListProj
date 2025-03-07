package ListProj.controllers;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ListProj.models.EntryModel;
import ListProj.models.loginModel;

//
@Controller
@RequestMapping("/login")
public class LoginController {

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

        model.addAttribute("loginModel", loginModel);
        model.addAttribute("type", "R");

        return "layouts/defaultLayout.html";
    }


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

}
