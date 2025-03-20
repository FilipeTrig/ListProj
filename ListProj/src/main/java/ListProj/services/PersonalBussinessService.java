package ListProj.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<PersonalModel> getPersonal(String name) {
        return PersonalDB.getPersonal(name);
    }

    @Override
    public PersonalModel updatePersonal(PersonalModel person) {
        return PersonalDB.updatePersonal(person);
    }

    @Override
    public boolean deletePersonal(String name) {
        return PersonalDB.deletePersonal(name);
    }

    @Override
    public int addPersonal(PersonalModel person) {
        return PersonalDB.addPersonal(person);
    }

    @Override
    public boolean checkPassword(String name, String password) {
        return PersonalDB.checkPassword(name, password);
    }
    
}
