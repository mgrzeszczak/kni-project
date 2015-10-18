package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Note;

/**
 * Created by Maciej on 17.10.2015.
 */
public interface NoteRepository extends CrudRepository<Note,Long>{



}
