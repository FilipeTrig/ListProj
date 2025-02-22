package ListProj.data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ListProj.models.EntryModel;

public interface EntryDataAcessInterface {
    
    public EntryModel getByDate(LocalDate date);

    public ArrayList<EntryModel> getAllEntries();

    public ArrayList<EntryModel> getEntriesRange(LocalDate startDate, LocalDate endDate);

    public long addOne(EntryModel entry);

    public boolean deleteOne(LocalDate date);

    public EntryModel updateOne(LocalDate date,EntryModel entry);


}
