package ListProj.data;

import java.util.List;
import java.util.Optional;

import ListProj.models.EntryJSONModel;
import ListProj.models.EntryModel;
import ListProj.models.PersonalModel;

public interface PersonalDataAcessInterface {
    
    public Optional<PersonalModel> getPersonal(String name);

    public boolean checkPassword(String name, String password);

    public PersonalModel updatePersonal(PersonalModel person);

    public boolean deletePersonal(String name);

    public int addPersonal(PersonalModel person);
}
