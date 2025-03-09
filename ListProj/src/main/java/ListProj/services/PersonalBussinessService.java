package ListProj.services;

import org.springframework.beans.factory.annotation.Autowired;

import ListProj.data.EntryDataAcessInterface;
import ListProj.data.PersonalDataAcessInterface;
import ListProj.models.PersonalModel;

public class PersonalBussinessService implements PersonalBussinessServiceInterface {

     @Autowired
    PersonalDataAcessInterface PersonalDB;
    
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
    public void getPersonal(String name) {
        PersonalDB.getPersonal(name);
    }

    @Override
    public void updatePersonal(PersonalModel person) {
        PersonalDB.updatePersonal(person);
    }

    @Override
    public void deletePersonal(String name) {
        PersonalDB.deletePersonal(name);
    }

    @Override
    public void addPersonal(PersonalModel person) {
        PersonalDB.addPersonal(person);
    }
    
}
