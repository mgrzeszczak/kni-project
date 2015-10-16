package pl.kni.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Semester;
import pl.kni.models.Subject;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface SubjectRepository extends CrudRepository<Subject,Long>{

    @Query("select s from Subject s where s.name=?1 and s.semester.id=?2")
    Subject findByNameAndSemesterId(String name, long id);
}
