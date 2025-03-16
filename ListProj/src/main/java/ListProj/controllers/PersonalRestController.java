package ListProj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ListProj.models.EntryModel;
import ListProj.services.EntryBussinessServiceInterface;
import ListProj.services.PersonalBussinessServiceInterface;

@RestController
@RequestMapping("/api/v1/personal")
public class PersonalRestController {
    
    //@Autowired
    PersonalBussinessServiceInterface service;

    @Autowired
    public PersonalRestController(PersonalBussinessServiceInterface service) {
        super();
        this.service = service;
    }
    
    @GetMapping("/login")
    public List<EntryModel> ShowAllPersonal(Model model) {
                
        List<EntryModel> EntryList = service.;
        
        return EntryList;
    }

}
