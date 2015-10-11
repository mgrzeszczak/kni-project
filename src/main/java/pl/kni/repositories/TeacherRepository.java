package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Teacher;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface TeacherRepository extends CrudRepository<Teacher,Long> {
}
