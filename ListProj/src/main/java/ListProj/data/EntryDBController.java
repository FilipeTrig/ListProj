package ListProj.data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import ListProj.models.EntryModel;


@Repository
public class EntryDBController implements EntryDataAcessInterface {

    ArrayList<EntryModel> EntryList = new ArrayList<EntryModel>();

    public EntryDBController() {
        //super();  
        ArrayList<Boolean> items = new ArrayList<Boolean>(54);
        for (int i = 0; i < 54; i++) {
            items.add(false);
        }
        EntryList.add(new EntryModel(LocalDate.now(), items,80));  
        EntryList.add(new EntryModel(LocalDate.of(2025,1,LocalDate.now().getDayOfMonth()-1), items,75));
        EntryList.add(new EntryModel(LocalDate.of(2025,1,LocalDate.now().getDayOfMonth()-2), items,68));
        EntryList.add(new EntryModel(LocalDate.of(2025,1,LocalDate.now().getDayOfMonth()-3), items,77));
        EntryList.add(new EntryModel(LocalDate.of(2025,1,LocalDate.now().getDayOfMonth()-4), items,81));
        EntryList.add(new EntryModel(LocalDate.of(2025,1,LocalDate.now().getDayOfMonth()-5), items,82));          
    }

    @Override
    public EntryModel getByDate(LocalDate date) {
        for (EntryModel entry : EntryList) {
            if (entry.getDate().equals(date)) {
                return entry;
            }
        }
        return null; // not found return null
    }

    @Override
    public ArrayList<EntryModel> getAllEntries() {
        return EntryList;
    }

    @Override
    public ArrayList<EntryModel> getEntriesRange(LocalDate startDate, LocalDate endDate) {
        
        ArrayList<EntryModel> foundItems = new ArrayList<EntryModel>();
        
        for (EntryModel entry : EntryList) {
            if (entry.getDate().isAfter(startDate) && entry.getDate().isBefore(endDate)) {
                foundItems.add(entry);
            }
        }
        return foundItems; //return list of orders with search term
        /* 
        ArrayList<EntryModel> foundItems = EntryList //same thing as before but with streams
            .stream()
            .filter(entry -> entry.getDate().isAfter(startDate) && entry.getDate().isBefore(endDate))
            .collect(Collectors.toList());
        return foundItems;
        */
    }

    @Override
    public long addOne(EntryModel entry) {
        boolean success = EntryList.add(entry);

        if (success) {
            return 1;//order.getId();
        } else {
            return 0;
        }
    }

    @Override
    public boolean deleteOne(LocalDate date) {
        boolean success = EntryList.removeIf(item -> item.getDate().equals(date));

        if (success) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EntryModel updateOne(LocalDate date,EntryModel entry) {

        for (int i = 0; i < EntryList.size(); i++) {
            EntryModel item = EntryList.get(i);
            if (item.getDate().equals(date)) {
                EntryList.set(i, entry);
                return entry;
            }
        }
        return null;
    }
    
}
