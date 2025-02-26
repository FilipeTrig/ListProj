package ListProj.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

import ListProj.models.EntryMapper;
import ListProj.models.EntryModel;

public class EntryClientService implements EntryDataAcessInterface {

    @Autowired
    DataSource dataSource;

    private JdbcClient jdbcClient;

    public EntryClientService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    @Override
    public Optional<EntryModel> getByDate(LocalDate date) {
        return jdbcClient.sql("SELECT * FROM ENTRIES WHERE DATE = :date")
                .param("date", date)
                .query(EntryModel.class).optional();
    }

    @Override
    public List<EntryModel> getAllEntries() {
        return jdbcClient.sql("SELECT * FROM ENTRIES")
                .query(EntryModel.class)
                .list();
    }

    @Override
    public List<EntryModel> getEntriesRange(LocalDate startDate, LocalDate endDate) {
        return jdbcClient.sql("SELECT * FROM ENTRIES WHERE DATE BETWEEN ? AND ?")
                //.param("startDate",startDate)
                //.param("endDate",endDate)
                .params(startDate,endDate)
                .query(EntryModel.class)
                .list();
    }

    @Override
    public int addOne(EntryModel entry) {
        var updated = jdbcClient.sql("INSERT INTO ENTRIES(ID,DATE,ITEMS,WEIGHT) values(?,?,?,?)")
                .params(List.of(entry.getId(),entry.getDate(),entry.getItems(),entry.getWeight()))
                .update();

        Assert.state(updated == 1, "Failed to create Entry " + entry.getId());
        return updated;
    }

    @Override
    public boolean deleteOne(LocalDate date) {
        var updated = jdbcClient.sql("DELETE FROM ENTRIES WHERE DATE = ?")
                .params(date)
                .update();

        Assert.state(updated == 1, "Failed to delete entry with date " + date);
        return updated !=1; //if update returns 1 the update failed
    }

    @Override
    public EntryModel updateOne(LocalDate date, EntryModel entry) {
        var updated = jdbcClient.sql("INSERT INTO ENTRIES(ID,DATE,ITEMS,WEIGHT) values(?,?,?,?)")
                .params(List.of(entry.getId(),entry.getDate(),entry.getItems(),entry.getWeight()))
                .update();

        Assert.state(updated == 1, "Failed to update post " + entry.getId());
        return entry;
    }

}
