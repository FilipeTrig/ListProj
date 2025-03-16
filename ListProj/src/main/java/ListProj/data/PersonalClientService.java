package ListProj.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

import ListProj.models.EntryJSONModel;
import ListProj.models.EntryModel;
import ListProj.models.PersonalModel;

public class PersonalClientService implements PersonalDataAcessInterface {
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    JdbcClient jdbcClient;

    public PersonalClientService() {
        super();
    }

    public Optional<PersonalModel> getPersonal(String name, PersonalClientService PersonalClientService) {
        this.jdbcClient = PersonalClientService.jdbcClient;        
        return getPersonal(name);
    }

    @Override
    public Optional<PersonalModel> getPersonal(String name) {
        
        PersonalModel person= new PersonalModel();
        try {
            person = jdbcClient.sql("SELECT * FROM personal WHERE NAME = ?")
            .param(name)
            .query(PersonalModel.class)
            .single();
        } catch (EmptyResultDataAccessException e) {
            // return error message "person not found"
            person=null;
        }    
        //person.setName(name);
        return Optional.of(person);
    }

    @Override
    public PersonalModel updatePersonal(PersonalModel person) {
        var updated = jdbcClient.sql("UPDATE personal SET PASSWORD=?,WEIGHT=? WHERE NAME = ?")
                .params(List.of(person.getPassword(),person.getWeight(),person.getName()))
                .update();
        Assert.state(updated == 1, "Failed to update Entry " + person.getName());
        return person;
    }

    @Override
    public boolean deletePersonal(String name) {
        var updated = jdbcClient.sql("DELETE FROM personal WHERE NAME = ?")
                .params(name)
                .update();

        Assert.state(updated == 1, "Failed to delete personal data for " + name);
        return updated !=1; //if update returns 1 the update failed
    }

    @Override
    public int addPersonal(PersonalModel person) {
        var updated = jdbcClient.sql("INSERT INTO personal(NAME,PASSWORD,WEIGHT) values(?,?,?)")
                .params(List.of(person.getName(),person.getPassword(),person.getWeight()))
                .update();
        Assert.state(updated == 1, "Failed to create Entry " + person.getName());
        return updated;
    }

    @Override
    public boolean checkPassword(String name, String password) {
        PersonalModel person= new PersonalModel();
        try {
            person = jdbcClient.sql("SELECT * FROM personal WHERE NAME = ? and PASSWORD = ?")
            .params(List.of(name,password))
            .query(PersonalModel.class)
            .single();
        } catch (EmptyResultDataAccessException e) {
            // return error message "person not found"
            person=null;
        }    
        //person.setName(name);
        return person!=null;
    }
}
