package pl.kni.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.kni.forms.TeacherCreateForm;
import pl.kni.services.TeacherService;

/**
 * Created by Maciej on 16.10.2015.
 */
@Component
public class TeacherCreateFormValidator implements Validator {

    @Autowired
    private TeacherService teacherService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TeacherCreateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TeacherCreateForm form = (TeacherCreateForm)o;

    }

    public void checkNameAvailability(TeacherCreateForm form,  Errors errors){

    }


}
