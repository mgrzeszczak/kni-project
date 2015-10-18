package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Book;

/**
 * Created by Maciej on 18.10.2015.
 */
public interface BookRepository extends CrudRepository<Book,Long> {

    Book findByAuthorAndTitle(String author, String title);
}
