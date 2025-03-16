package ListProj.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ListProj.data.EntryDataAcessInterface;
import ListProj.models.PersonalModel;

public interface PersonalBussinessServiceInterface {

    public void init();

    public void destroy();

    public Optional<PersonalModel> getPersonal(String name);

    public boolean checkPassword(String name, String password);

    public PersonalModel updatePersonal(PersonalModel person);

    public boolean deletePersonal(String name);

    public int addPersonal(PersonalModel person);
    
}
