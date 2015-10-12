package pl.kni.models;


import pl.kni.forms.UserCreateForm;
import pl.kni.security.Role;


import javax.persistence.*;

/**
 * Created by Maciej on 12.10.2015.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    // TODO: email activation
    //private boolean activated;
    //private String activationToken;

    public User(UserCreateForm form) {
        email = form.getEmail();
        password = form.getPassword();
        role = form.getRole();
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
