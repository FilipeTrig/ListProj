package ListProj.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonalModel {

    @NotNull(message="Username is required")
    @Size(min=3, max=20,message="Username must be between 3 and 20 characters")
    private String Name;
    @NotNull(message="Password is required")
    @Size(min=8, max=20,message="Password must be between 8 and 20 characters")
    private String Password;
    private int weight;

    

    public PersonalModel() {
    }

    public PersonalModel(String Name, String Password, int weight) {
        this.Name = Name;
        this.Password = Password;
        this.weight = weight;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
