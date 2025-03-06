package ListProj.models;

public class PersonalModel {

    private String Name;
    private String Password;
    private int weight;

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
