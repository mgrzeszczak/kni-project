package pl.kni.services;

import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.BookCreateForm;
import pl.kni.models.Book;

import java.util.Optional;

/**
 * Created by Maciej on 18.10.2015.
 */
public interface BookService {

    Optional<Book> findById(long id);
    void remove(long id);
    Book create(BookCreateForm bookCreateForm, long subjectId) throws SubjectNotFoundException;
    Book checkIfExists(String title, String author);


}
