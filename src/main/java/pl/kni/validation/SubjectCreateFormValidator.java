package pl.kni.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.MajorCreateForm;
import pl.kni.forms.SubjectCreateForm;
import pl.kni.services.MajorService;
import pl.kni.services.SubjectService;

/**
 * Created by Maciej on 16.10.2015.
 */
@Component
public class SubjectCreateFormValidator implements Validator {

    @Autowired
    private SubjectService subjectService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(SubjectCreateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SubjectCreateForm form = (SubjectCreateForm)o;
        checkNameAvailability(form, errors);
    }

    private void checkNameAvailability(SubjectCreateForm form,  Errors errors){
        try {
            subjectService.findByNameAndSemesterId(form.getName(),form.getSemesterId());
        } catch (SubjectNotFoundException e) {
            return;
        }
        errors.rejectValue("name","name_taken","Subject already exists.");
    }
}