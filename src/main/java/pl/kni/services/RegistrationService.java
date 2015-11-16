package pl.kni.services;

import pl.kni.forms.UserCreateForm;
import pl.kni.models.Registration;
import pl.kni.models.User;

/**
 * Created by Maciej on 16.11.2015.
 */
public interface RegistrationService {

    Registration create(UserCreateForm form, String baseUrl);
    User confirm(String token, String email);
    boolean emailAvailable(String email);

}
