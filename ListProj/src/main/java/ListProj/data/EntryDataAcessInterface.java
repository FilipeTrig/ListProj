package ListProj.data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import ListProj.models.EntryModel;

public interface EntryDataAcessInterface {
    
    public Optional<EntryModel> getByDate(LocalDate date);

    public List<EntryModel> getAllEntries();

    public List<EntryModel> getEntriesRange(LocalDate startDate, LocalDate endDate);

    public int addOne(EntryModel entry);

    public boolean deleteOne(LocalDate date);

    public EntryModel updateOne(LocalDate date,EntryModel entry);


}
