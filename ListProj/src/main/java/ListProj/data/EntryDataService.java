package ListProj.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import ListProj.models.EntryModel;
import ListProj.models.EntryMapper;

public class EntryDataService implements EntryDataAcessInterface {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<EntryModel> getByDate(LocalDate date) {
        //EntryDataService entryDataService = new EntryDataService();
        List <EntryModel> ListR = jdbcTemplate.query("SELECT * FROM entries WHERE DATE =?", new EntryMapper(),date);
        return Boolean.parseBoolean(ListR.get(0).getItems().toString()) ? Optional.of(ListR.get(0)) : null;
                            //entryDataService.convertItemsJSON(entry.getItems(),   
                            //date);
        //return results.size() > 0 ? results.get(0) : null; //get(0) is not a good practice
    }

    @Override
    public ArrayList<EntryModel> getAllEntries() {
        //EntryModel entry = new EntryModel();
        JSONData jsonData = new JSONData();
        List<EntryModel> entries=jdbcTemplate.query("SELECT * FROM entries", new EntryMapper());
        ArrayList<EntryModel> results= new ArrayList<EntryModel>();
        for (EntryModel it : entries) {
            /*
            try {
                it.setItems(jsonData.readJSON(it.getItems()));
                it.setWeight(it.getWeight());
                //it.setId(jsonData.readJSON(it.getId()));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                */
            results.add(it);
        }
        return results;
    }

    @Override
    public ArrayList<EntryModel> getEntriesRange(LocalDate startDate, LocalDate endDate) { 
        //JSONData jsonData = new JSONData();      
        List<EntryModel> entries = jdbcTemplate.query("SELECT * FROM entries WHERE DATE BETWEEN ? AND ?", new EntryMapper(), startDate, endDate);
        ArrayList<EntryModel> results= new ArrayList<EntryModel>(); 
        for (EntryModel it : entries) {
            /*
            try {
                it.setItems(jsonData.readJSON(it.getItems()));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            */
            results.add(it);
        }
        return results;
    }

    @Override
    public int addOne(EntryModel entry) {

        //JSONData jsonData = new JSONData();

        // convert entry from JSON to ArrayList
        /* 
        try {
            entry.setItems(jsonData.writeJSON(entry.getItems()));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate)
                                                .withTableName("entries");
        
        // creating an hash map to "decode" the object into a map
        
        Map<String, Object> parameters = Map.of(
            "DATE", entry.getDate(),
            "ITEMS", entry.getItems(),
            "WEIGHT", entry.getWeight(),
            "ID", entry.getId()
        );

        Number result = simpleInsert.executeAndReturnKey(parameters);
        return result.intValue();
    }

    @Override
    public boolean deleteOne(LocalDate date) {
        long result = jdbcTemplate.update("DELETE FROM entries WHERE DATE = ?", date);
        return result>0 ? true : false;
    }

    @Override
    public EntryModel updateOne(LocalDate date, EntryModel entry) {
        JSONData jsonData = new JSONData();

        try {
            entry.setItems(jsonData.writeJSON(entry.getItems()));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int result=jdbcTemplate.update("UPDATE entries SET ITEMS = ?, WEIGHT=? WHERE DATE = ?",
                            entry.getItems(),
                            entry.getWeight(),   
                            date);
        return result>0 ? entry : null;
    }

    
    
    
}
