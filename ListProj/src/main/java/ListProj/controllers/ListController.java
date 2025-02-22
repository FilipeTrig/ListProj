package ListProj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/List")
public class ListController {
    
    @GetMapping("/")
    public String displayList(Model model) {

        model.addAttribute("EntryModel", model);
        model.addAttribute("type", "E");
        for (int i = 1; i < 54; i++) {
            model.addAttribute("item"+String.valueOf(i), false);
            //System.out.println("item"+String.valueOf(i));
        }
        return "layouts/defaultLayout.html";
    }
}
