package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Faculty;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface FacultyRepository extends CrudRepository<Faculty,Long> {

    Faculty findByAbbrev(String abbrev);
    Faculty findByName(String name);
}
