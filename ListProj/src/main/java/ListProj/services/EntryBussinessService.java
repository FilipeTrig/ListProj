
package ListProj.services;



import ListProj.data.EntryDataAcessInterface;
import ListProj.models.EntryJSONModel;
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
    public List<EntryModel> getEntriesRange(String startDate, String endDate) {
        return EntryDB.getEntriesRange(startDate, endDate);
    }   

    @Override
    public Optional<EntryModel> getByDate(String date) {
        return EntryDB.getByDate(date);
    }

    @Override
    public long addOne(EntryJSONModel entry) {
            return EntryDB.addOne(entry);
    }

    @Override
    public EntryJSONModel updateOne(String date,EntryJSONModel entry) {
            return EntryDB.updateOne(date, entry);
    }

    @Override
    public boolean deleteOne(String date) {
            return EntryDB.deleteOne(date);
    }

    @Override
    public Optional<EntryModel> getByID(int ID) {
        return EntryDB.getByID(ID);
    }

    @Override
    public Optional<EntryModel> getMinID() {
        return EntryDB.getMinID();
    }

    @Override
    public Optional<EntryModel> getMaxID() {
        return EntryDB.getMaxID();
    }

    @Override
    public List<EntryModel> getEntriesRangebyID(int minID, int maxID) {
        return EntryDB.getEntriesRangebyID(minID, maxID);
    }  

        
}

