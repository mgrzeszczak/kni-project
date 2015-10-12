package pl.kni.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.kni.forms.UserCreateForm;


/**
 * Created by Maciej on 12.10.2015.
 */
@Component
public class UserCreateFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserCreateForm form = (UserCreateForm)o;
        validatePasswords(errors,form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form){
        if (!form.getPassword().equals(form.getPasswordRepeated())){
            errors.rejectValue("password","no_match","passwords do not match");
        }
    }
}
