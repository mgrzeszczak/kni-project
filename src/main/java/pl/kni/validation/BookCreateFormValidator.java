package pl.kni.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.kni.forms.BookCreateForm;
import pl.kni.models.Book;
import pl.kni.services.BookService;

/**
 * Created by Maciej on 18.10.2015.
 */
@Component
public class BookCreateFormValidator implements Validator {

    @Autowired
    private BookService bookService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(BookCreateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookCreateForm form = (BookCreateForm)o;
        checkIfExists(form,errors);
    }

    private void checkIfExists(BookCreateForm form,  Errors errors){
        if (bookService.checkIfExists(form.getTitle(),form.getAuthor())!=null) errors.reject("exists");
    }
}
