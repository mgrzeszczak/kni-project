package pl.kni.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Semester;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface SemesterRepository extends CrudRepository<Semester,Long> {

    @Query("select s from Semester s where s.number=?3 and s.major.abbrev=?2 and s.major.faculty.abbrev=?1")
    Semester findByFacultyMajorAndNumber(String faculty, String major, int number);

}
