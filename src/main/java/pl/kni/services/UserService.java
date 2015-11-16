package pl.kni.services;

import pl.kni.exceptions.UserNotFoundException;
import pl.kni.forms.UserCreateForm;
import pl.kni.models.Registration;
import pl.kni.models.User;

import java.util.List;

/**
 * Created by Maciej on 12.10.2015.
 */
public interface UserService {

    User findByEmail(String email) throws UserNotFoundException;
    User findById(long id) throws UserNotFoundException;
    List<User> findAll();
    User create(UserCreateForm userCreateForm);
    User create(Registration registration);
    boolean emailAvailable(String email);
}
