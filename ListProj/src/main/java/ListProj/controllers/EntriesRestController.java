package ListProj.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

//import org.apache.catalina.servlets.DefaultServlet.SortManager.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import ListProj.models.EntryJSONModel;
import ListProj.models.EntryModel;
import ListProj.services.EntryBussinessServiceInterface;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/entries")
public class EntriesRestController {

    //@Autowired
    EntryBussinessServiceInterface service;

    @Autowired
    public EntriesRestController(EntryBussinessServiceInterface service) {
        super();
        this.service = service;
    }
    
    @GetMapping("/")
    public List<EntryModel> ShowAllEntries(Model model) {
                
        List<EntryModel> EntryList = service.getAllEntries();
        
        return EntryList;
    }

    @GetMapping("/search/") //{fromDate}{toDate}
    public List<EntryModel> SearchOrders(@RequestParam() String fromDate
                                        ,@RequestParam() String toDate) { //used the variable in the URL to search for orders
 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        LocalDate from = LocalDate.parse(fromDate, formatter);
        LocalDate to = LocalDate.parse(toDate, formatter); 
        List<EntryModel> EntryList = service.getEntriesRange(from.minusDays(1).toString(), to.plusDays(1).toString());        
        return EntryList;
    }

    @PostMapping("/")
    public long addEntry(@RequestBody EntryJSONModel item) {
        return service.addOne(item);
    }

    @GetMapping("/getOne/")
    public Optional<EntryModel> getOne(@RequestParam() String date) {
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;  
        //LocalDate returnDate = LocalDate.parse(date, formatter);
        return service.getByDate(date);
    }
    
    @GetMapping("/delete/")
    public boolean deleteOne(@RequestParam() String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");   
        //LocalDate returnDate = LocalDate.parse(date, formatter);
        return service.deleteOne(date);
    }

    @PutMapping("/update/")
    public EntryJSONModel updateOrder(@RequestParam() String date, @RequestBody EntryJSONModel entry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        //LocalDate returnDate = LocalDate.parse(date, formatter);
        return service.updateOne(date, entry);
    }
}