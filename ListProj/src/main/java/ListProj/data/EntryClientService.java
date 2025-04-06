package ListProj.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jdbc.core.convert.DefaultJdbcTypeFactory;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

import ListProj.models.EntryJSONModel;
import ListProj.models.EntryMapper;
import ListProj.models.EntryModel;

public class EntryClientService implements EntryDataAcessInterface {

    @Autowired
    DataSource dataSource;
    
    @Autowired
    JdbcClient jdbcClient;

    public EntryClientService() {
        super();
    }

    public void init() {
    System.out.println("OrdersBussinessService2: init method called");
    //EntryList = new ArrayList<EntryModel>();
    }
    
    @Override
    public Optional<EntryModel> getByDate(String date) {
        //JSONData JSONdata= new JSONData();
        EntryModel entry= new EntryModel();
        EntryJSONModel JSONentry= new EntryJSONModel();
        try {
        JSONentry = jdbcClient.sql("SELECT * FROM entries WHERE DATE = ?")
            .param(date)
            .query(EntryJSONModel.class)
            .single();
        } catch (EmptyResultDataAccessException e) {
            JSONentry.setDate(LocalDate.parse(date));
            String items="";
            for (int i = 0; i < 54; i++) {
                items = items+"false";
                if (i <53) items = items+",";
            }
            JSONentry.setItems(items);
            JSONentry.setWeight(0);
            addOne(JSONentry);    
            JSONentry = jdbcClient.sql("SELECT * FROM entries WHERE DATE = ?")
                .param(date)
                .query(EntryJSONModel.class)
                .single();
        }    
        entry.setId(JSONentry.getId());
        entry.setItems(JSONData.readJSONItems(JSONentry.getItems()));
        entry.setDate(JSONentry.getDate());
        entry.setWeight(JSONentry.getWeight());
        return Optional.of(entry);
    }

    @Override
    public List<EntryModel> getAllEntries() {
        return jdbcClient.sql("SELECT * FROM entries")
                .query(EntryModel.class)
                .list();
    }

    @Override
    public List<EntryModel> getEntriesRange(String startDate, String endDate) {
        return jdbcClient.sql("SELECT * FROM entries WHERE DATE BETWEEN ? AND ?")
                //.param("startDate",startDate)
                //.param("endDate",endDate)
                .params(startDate,endDate)
                .query(EntryModel.class)
                .list();
    }

    @Override
    public Optional<EntryModel> getByID(int ID) {
        EntryModel entry= new EntryModel();
        EntryJSONModel JSONentry= new EntryJSONModel();
        JSONentry= jdbcClient.sql("SELECT * FROM entries WHERE ID = ?")
                .param(ID)
                .query(EntryJSONModel.class)
                .single();
                entry.setId(JSONentry.getId());
                entry.setItems(JSONData.readJSONItems(JSONentry.getItems()));
                entry.setDate(JSONentry.getDate());
                entry.setWeight(JSONentry.getWeight());
                return Optional.of(entry);
    }

    @Override
    public Optional<EntryModel> getMinID() {
        EntryModel entry= new EntryModel();
        EntryJSONModel JSONentry= new EntryJSONModel();
        JSONentry=jdbcClient.sql("SELECT * FROM entries WHERE ID = (SELECT MIN(ID) FROM entries)")
                .query(EntryJSONModel.class)
                .single();
                entry.setId(JSONentry.getId());
                entry.setItems(JSONData.readJSONItems(JSONentry.getItems()));
                entry.setDate(JSONentry.getDate());
                entry.setWeight(JSONentry.getWeight());
                return Optional.of(entry);
    }

    @Override
    public Optional<EntryModel> getMaxID() {
        EntryModel entry= new EntryModel();
        EntryJSONModel JSONentry= new EntryJSONModel();
        JSONentry=jdbcClient.sql("SELECT * FROM entries WHERE ID = (SELECT MAX(ID) FROM entries)")
                .query(EntryJSONModel.class)
                .single();
                entry.setId(JSONentry.getId());
                entry.setItems(JSONData.readJSONItems(JSONentry.getItems()));
                entry.setDate(JSONentry.getDate());
                entry.setWeight(JSONentry.getWeight());
                return Optional.of(entry);
    }

    @Override
    public List<EntryModel> getEntriesRangebyID(int minID, int maxID) {
        return jdbcClient.sql("SELECT * FROM entries WHERE ID BETWEEN ? AND ?")
                .params(minID,maxID)
                .query(EntryModel.class)
                .list();
    }


    @Override
    public int addOne(EntryJSONModel entry) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        EntryJSONModel JSONentry= new EntryJSONModel();
        JSONentry.setId(entry.getId());
        JSONentry.setItems(entry.getItems()); //JSONData.writeJSONItems(entry.getItems())
        JSONentry.setDate(entry.getDate());
        JSONentry.setWeight(entry.getWeight());
        var updated = jdbcClient.sql("INSERT INTO entries(DATE,ITEMS,WEIGHT) values(?,?,?)")
                .params(List.of(JSONentry.getDate(),JSONentry.getItems(),JSONentry.getWeight()))
                .update();
        Assert.state(updated == 1, "Failed to create Entry " + entry.getId());
        return updated;
    }

    @Override
    public boolean deleteOne(String date) {
        var updated = jdbcClient.sql("DELETE FROM entries WHERE DATE = ?")
                .params(date)
                .update();

        Assert.state(updated == 1, "Failed to delete entry with date " + date);
        return updated !=1; //if update returns 1 the update failed
    }

    @Override
    public EntryJSONModel updateOne(String date, EntryJSONModel entry) {
        EntryJSONModel JSONentry= new EntryJSONModel();
        //JSONentry.setId(entry.getId());
        JSONentry.setItems(entry.getItems()); //JSONData.writeJSONItems(entry.getItems())
        JSONentry.setDate(entry.getDate());
        JSONentry.setWeight(entry.getWeight());
        var updated = jdbcClient.sql("UPDATE entries SET ITEMS=?,WEIGHT=? WHERE DATE = ?")
                .params(List.of(entry.getItems(),entry.getWeight(),entry.getDate()))
                .update();

        Assert.state(updated == 1, "Failed to update post " + entry.getId());
        return entry;
    }    

}
