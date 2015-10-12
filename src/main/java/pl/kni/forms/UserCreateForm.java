package pl.kni.forms;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import pl.kni.security.Role;
import pl.kni.validation.Available;
import pl.kni.validation.UniversityEmail;

import javax.validation.constraints.NotNull;

/**
 * Created by Maciej on 12.10.2015.
 */
public class UserCreateForm {

    @Email
    @Available(type = Available.Field.EMAIL)
    @UniversityEmail(domain = "pw.edu.pl")
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordRepeated;
    @NotNull
    private Role role = Role.USER;

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

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
