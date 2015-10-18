package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.BookCreateForm;
import pl.kni.models.Book;
import pl.kni.models.Subject;
import pl.kni.repositories.BookRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Maciej on 18.10.2015.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SubjectService subjectService;

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(bookRepository.findOne(id));
    }

    @Override
    public void remove(long id) {
        Optional<Book> book = findById(id);
        if (!book.isPresent()) return;
        bookRepository.delete(book.get());
    }

    @Override
    public Book create(BookCreateForm bookCreateForm, long subjectId) throws SubjectNotFoundException{
        Subject subject = subjectService.findById(subjectId);
        Book book = new Book();
        book.setSubject(subject);
        book.setAuthor(bookCreateForm.getAuthor());
        book.setTitle(bookCreateForm.getTitle());
        return bookRepository.save(book);
    }

    @Override
    public Book checkIfExists(String title, String author) {
        return bookRepository.findByAuthorAndTitle(author, title);
    }
}
