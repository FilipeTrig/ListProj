package ListProj.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ListProj.data.EntryClientService;
import ListProj.models.EntryModel;
import ListProj.services.EntryBussinessService;
import ListProj.services.EntryBussinessServiceInterface;

@Controller
@RequestMapping("/List")
public class ListController {

    EntryBussinessServiceInterface service;

    @Autowired
    public ListController(EntryBussinessServiceInterface service) {
        super();
        this.service = service;
    }
    
    @GetMapping("/")
    public String displayList(Model model) {

        model.addAttribute("EntryJSONModel", model);
        model.addAttribute("type", "E");
        //EntryBussinessService EntryBussinessService = new EntryBussinessService();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        String date = LocalDate.now().format(formatter);
        EntryModel entry=getItemsDB(date).get();
        ArrayList<Boolean> items = entry.getItems();
        for (int i = 1; i < 53; i++) {            
            model.addAttribute("item"+String.valueOf(i), items.get(i));
        }
        return "layouts/defaultLayout.html";
    }

    @GetMapping("/getItemsDB/")
    public Optional<EntryModel> getItemsDB(@RequestParam() String date) {
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;  
        //LocalDate returnDate = LocalDate.parse(date, formatter);        
        return service.getByDate(date);
    }

    @GetMapping("/getItems/")
    public String getItems(Model model) {
        String items="";
        for (int i = 1; i < 54; i++) {
            items=items+model.getAttribute("item"+String.valueOf(i)).toString();
            if (i <53) items=items+(",");
            //model.addAttribute("item"+String.valueOf(i), false);
            //System.out.println("item"+String.valueOf(i));
        }        
        return items;
    }
}
