package pl.kni.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.forms.FacultyCreateForm;
import pl.kni.forms.UserCreateForm;
import pl.kni.repositories.FacultyRepository;
import pl.kni.services.FacultyService;

/**
 * Created by Maciej on 15.10.2015.
 */
@Component
public class FacultyCreateFormValidator implements Validator {

    @Autowired
    private FacultyService facultyService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(FacultyCreateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FacultyCreateForm form = (FacultyCreateForm)o;
        checkAbbrevAvailability(form,errors);
        checkNameAvailability(form,errors);
    }

    private void checkNameAvailability(FacultyCreateForm form,  Errors errors){
        try {
            facultyService.findByAbbrev(form.getAbbrev());
        } catch (FacultyNotFoundException e) {
            return;
        }
        errors.rejectValue("abbrev","abbrev_taken","Abbreviation already taken.");
    }
    private void checkAbbrevAvailability(FacultyCreateForm form,  Errors errors){
        try {
            facultyService.findByName(form.getName());
        } catch (FacultyNotFoundException e) {
            return;
        }
        errors.rejectValue("name","name_taken","Faculty already exists.");
    }
}
