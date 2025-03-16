package ListProj.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ListProj.data.EntryClientService;
import ListProj.models.EntryJSONModel;
import ListProj.models.EntryModel;
import ListProj.models.ItemsModel;
import ListProj.models.PersonalModel;
import ListProj.models.loginModel;
import ListProj.services.EntryBussinessService;
import ListProj.services.EntryBussinessServiceInterface;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/List")
public class ListController {

    EntryBussinessServiceInterface service;

    @Autowired
    public ListController() {
        super();
        //this.service = service;
    }
    
    @GetMapping("/")
    public String displayList(Model model) {

        if (model.getAttribute("logged")!="true") {
            model.addAttribute("loginModel", new loginModel());
            model.addAttribute("type", "L"); //return to login screen if not logged in
            return "redirect:/login/";
        }
        //EntryBussinessService EntryBussinessService = new EntryBussinessService();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        String date = LocalDate.now().format(formatter);
        EntryModel entry=getItemsDB(date).get();
        ArrayList<Boolean> items = entry.getItems(); 
        Boolean[] itemsArray = new Boolean[54];       
        for (int i = 0; i < 54; i++) {            
            itemsArray[i]= items.get(i);
        }
        model.addAttribute("itemsModel", new ItemsModel(itemsArray));
        model.addAttribute("type", "E");
        return "layouts/defaultLayout.html";
    }


    //@GetMapping("/getItemsDB/")
    public Optional<EntryModel> getItemsDB(@RequestParam() String date) {
        
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;  
        //LocalDate returnDate = LocalDate.parse(date, formatter);        
        return service.getByDate(date);
    }

    @PostMapping("/getItemsHTML/")
    public String getItemsHTML(@ModelAttribute ItemsModel itemsModel, Model model) {

        /*
        model.addAttribute("itemsModel", new ItemsModel());
        model.addAttribute("type", "E");
        
        Boolean[] items=itemsModel.getAllItems();
        for (int i = 0; i < 54; i++) {   
            var item = Boolean.valueOf(model.getAttribute("item" + (i + 1))==null ? "false" : model.getAttribute("item" + (i + 1)).toString());
            items[i]=item;
        }
        itemsModel.setAllItems(items);
        */
        //model.addAttribute("ItemsModel", itemsModel);
        //model.addAttribute("type", "E");
        String items="";
        for (int i = 0; i < 54; i++) {            
            items=items+(itemsModel.getAllItems()[i]);
            if (i <53) items = items+",";
        }
        /*
        for (int i = 0; i < 54; i++) {
            model.getAttribute("item"+String.valueOf(i));
        }
        */ 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        String date = LocalDate.now().format(formatter); 
        EntryJSONModel entry = new EntryJSONModel();
        entry.setDate(LocalDate.parse(date));
        entry.setItems(items);
        entry.setWeight(0);
        service.updateOne(date, entry); 
        
        model.addAttribute("type", "E");
        return "layouts/defaultLayout.html";
    }
}
