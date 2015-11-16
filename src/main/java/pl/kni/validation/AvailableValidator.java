package pl.kni.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kni.services.RegistrationService;
import pl.kni.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maciej on 12.10.2015.
 */
public class AvailableValidator implements ConstraintValidator<Available,String> {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    private Available.Field type;

    public void initialize(Available available) {
        this.type = available.type();
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.emailAvailable(s)&&registrationService.emailAvailable(s);
    }
}
