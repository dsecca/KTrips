package test.ktrips;

/**
 * Created by David on 2017-03-13.
 */

public class User {

    //Create the private data (Ahmed)
    private String username;
    private String email;
    private String password;
    private String country;
    private String DateOfBirth;


    //Constructor (Ahmed)
    public User() {
    }

    //Public methods  (Ahmed)
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}
