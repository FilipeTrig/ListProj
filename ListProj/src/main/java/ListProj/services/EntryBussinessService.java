
package ListProj.services;



import ListProj.data.EntryDataAcessInterface;
import ListProj.models.EntryModel;

import java.security.KeyStore.Entry;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;



public class EntryBussinessService implements EntryBussinessServiceInterface {

    @Autowired
    EntryDataAcessInterface EntryDB;

    @Override
    public void init() {
    System.out.println("OrdersBussinessService2: init method called");
    //EntryList = new ArrayList<EntryModel>();
    }

    @Override
    public void destroy() {
        System.out.println("OrdersBussinessService2: destroy method called");
    }

    @Override
    public void test() {
        System.out.println("This is a test");
    }


    @Override
    public List<EntryModel> getAllEntries() {
        return EntryDB.getAllEntries();
    }

    @Override
    public List<EntryModel> getEntriesRange(LocalDate startDate, LocalDate endDate) {
        return EntryDB.getEntriesRange(startDate, endDate);
    }   

    @Override
    public Optional<EntryModel> getByDate(LocalDate date) {
        return EntryDB.getByDate(date);
    }

    @Override
    public long addOne(EntryModel entry) {
            return EntryDB.addOne(entry);
    }

    @Override
    public EntryModel updateOne(LocalDate date,EntryModel entry) {
            return EntryDB.updateOne(date, entry);
    }

    @Override
    public boolean deleteOne(LocalDate date) {
            return EntryDB.deleteOne(date);
    }  

        
}

