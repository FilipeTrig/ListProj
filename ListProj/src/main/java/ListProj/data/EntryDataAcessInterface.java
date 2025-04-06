package ListProj.data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import ListProj.models.EntryJSONModel;
import ListProj.models.EntryModel;

public interface EntryDataAcessInterface {
    
    public Optional<EntryModel> getByDate(String date);

    public Optional<EntryModel> getByID(int ID);

    public Optional<EntryModel> getMinID();

    public Optional<EntryModel> getMaxID();

    public List<EntryModel> getAllEntries();

    public List<EntryModel> getEntriesRange(String startDate, String endDate);

    public List<EntryModel> getEntriesRangebyID(int minID, int maxID);

    public int addOne(EntryJSONModel entry);

    public boolean deleteOne(String date);

    public EntryJSONModel updateOne(String date,EntryJSONModel entry);


}
