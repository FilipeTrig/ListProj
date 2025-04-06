package ListProj.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ListProj.models.EntryModel;
import ListProj.models.Tripple;
import ListProj.models.loginModel;
import ListProj.services.EntryBussinessServiceInterface;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/Graph")
public class GrapthController {

    EntryBussinessServiceInterface service;

    @Autowired
    public GrapthController(EntryBussinessServiceInterface service) {
        super();
        this.service = service;
    }
    
    @GetMapping("/")
    public String displayHome(Model model) {
        /* 
        if (model.getAttribute("logged")!="true") {
            model.addAttribute("loginModel", new loginModel());
            model.addAttribute("type", "L"); //return to login screen if not logged in
            return "redirect:/login/";
        }
        */
        List<EntryModel> EntryListGraph = service.getEntriesRangebyID(service.getMinID().get().getId(), service.getMaxID().get().getId());
        Tripple[] trippleArray = buildArray(EntryListGraph);
        model.addAttribute("trippleArray", trippleArray);
        model.addAttribute("type", "G");
        return "layouts/defaultLayout.html";
    }

    @GetMapping("/getData") 
    public Tripple[] getData() {
        List<EntryModel> EntryListGraph = service.getEntriesRangebyID(service.getMinID().get().getId(), service.getMaxID().get().getId());
        Tripple[] trippleArray = buildArray(EntryListGraph);
        System.out.println("getData() called");
        return trippleArray;
    }

    public Tripple[] buildArray(List<EntryModel> EntryList) {
        Tripple[] trippleArray = new Tripple[EntryList.size()];
        for (EntryModel entry : EntryList) {
            int entryTrueCount = countEntries(entry.getItems());
            //String date = entry.getDate().toString();
            Tripple tripple = new Tripple(entry.getDate().toString(), entryTrueCount, entry.getWeight());
            trippleArray[EntryList.indexOf(entry)] = tripple;
            System.out.println(trippleArray[EntryList.indexOf(entry)].toString());
        }
        return trippleArray;
    }

    public int countEntries(ArrayList<Boolean> items) {
        int count = 0;
        for (boolean item : items) {
            if (item==true) {
                count++;
            }
        }
        return count;   
    }    

}
