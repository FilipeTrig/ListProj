package ListProj.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import ListProj.models.EntryModel;


public interface EntryBussinessServiceInterface {
    
    public void test();    

    public void init();

    public void destroy();

    public EntryModel getByDate(LocalDate date);

    public List<EntryModel> getAllEntries();

    public List<EntryModel> getEntriesRange(LocalDate startDate, LocalDate endDate);

    public long addOne(EntryModel entry);

    public boolean deleteOne(LocalDate date);

    public EntryModel updateOne(LocalDate date,EntryModel entry);
}
