package pl.kni.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kni.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maciej on 12.10.2015.
 */
public class UniversityEmailValidator implements ConstraintValidator<UniversityEmail,String> {

    private UniversityEmail universityEmail;

    @Override
    public void initialize(UniversityEmail universityEmail) {
        this.universityEmail = universityEmail;
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        final String domain = universityEmail.domain();
        if (s.length()<domain.length()) return false;
        return s.substring(s.length()-domain.length(),s.length()).equals(domain);
    }
}
