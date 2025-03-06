package ListProj.models;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EntryJSONModel {

    @Id
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String items;
    private int weight;
    
    public EntryJSONModel() {
    }

    public EntryJSONModel(int id, LocalDate date, String items, int weight) {
        this.id = id;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
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
        return "EntryJSONModel{" +
                "id=" + id +
                ", date=" + date +
                ", items='" + items + '\'' +
                ", weight=" + weight +
                '}';
    }
}

