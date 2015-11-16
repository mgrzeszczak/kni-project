package pl.kni.models;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.kni.forms.UserCreateForm;
import pl.kni.security.Role;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maciej on 16.11.2015.
 */
@Entity
public class Registration {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String token;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.DATE)
    private Date expires;

    public Registration(UserCreateForm form) {
        email = form.getEmail();
        password = new BCryptPasswordEncoder().encode(form.getPassword());
        role = form.getRole();
        date = new Date();
        expires = new Date(date.getTime()+7*24*60*60*1000);
        System.out.println(date.toString()+"\n"+expires.toString());
    }

    public Registration() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isValid(){
        Date now = new Date();
        return now.getTime()<=expires.getTime();
    }
}
