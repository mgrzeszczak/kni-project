package pl.kni.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.forms.FacultyCreateForm;
import pl.kni.forms.MajorCreateForm;
import pl.kni.services.FacultyService;
import pl.kni.services.MajorService;

/**
 * Created by Maciej on 15.10.2015.
 */
@Component
public class MajorCreateFormValidator implements Validator {

    @Autowired
    private MajorService majorService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(MajorCreateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MajorCreateForm form = (MajorCreateForm)o;
        checkNameAvailability(form, errors);
    }

    private void checkNameAvailability(MajorCreateForm form,  Errors errors){
        try {
            majorService.findByNameAndFacultyId(form.getName(),form.getFacultyId());
        } catch (MajorNotFoundException e) {
            return;
        }
        errors.rejectValue("name","name_taken","Major already exists.");
    }
}