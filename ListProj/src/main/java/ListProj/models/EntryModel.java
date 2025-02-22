package ListProj.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


public class EntryModel {
    
    //@Id
    private int id;
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private ArrayList<Boolean> items= new ArrayList<Boolean>(54);
    private int weight;


    public EntryModel() {
        super();
    }

    public EntryModel(LocalDate date, ArrayList<Boolean> items, int weight) {
        this.date = date;
        this.items = items;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Boolean> getItems() {
        return items;
    }

    public void setItems(ArrayList<Boolean> items) {
        this.items = items;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EntryModel{" +
            "date=" + date +
            ", items=" + items +
            '}';
    }   
    
    
}
