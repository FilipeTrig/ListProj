package ListProj.models;

//import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import jakarta.validation.constraints.Size;

public class loginModel {

    @NotNull(message="Username is required")
    @Size(min=3, max=20,message="Username must be between 3 and 20 characters")
    private String username;

    @NotNull(message="Password is required")
    @Size(min=8, max=20,message="Password must be between 8 and 20 characters")
    private String password;

    // Constructor

    public loginModel() {
        super();
    }
    /* 
    public loginModel(String username, String password) {
        this.username = username;
        this.password = password;
    } */

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method
    @Override
    public String toString() {
        return "loginModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
