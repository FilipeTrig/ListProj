package ListProj.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import ListProj.models.EntryJSONModel;
import ListProj.models.EntryModel;


public interface EntryBussinessServiceInterface {
    
    public void test();    

    public void init();

    public void destroy();

    public Optional<EntryModel> getByDate(String date);

    public Optional<EntryModel> getByID(int ID);

    public Optional<EntryModel> getMinID();

    public Optional<EntryModel> getMaxID();

    public List<EntryModel> getAllEntries();

    public List<EntryModel> getEntriesRange(String startDate, String endDate);

    public List<EntryModel> getEntriesRangebyID(int minID, int maxID);

    public long addOne(EntryJSONModel entry);

    public boolean deleteOne(String date);

    public EntryJSONModel updateOne(String date,EntryJSONModel entry);
}
