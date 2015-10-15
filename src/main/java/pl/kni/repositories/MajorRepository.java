package pl.kni.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Major;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface MajorRepository extends CrudRepository<Major,Long> {

    @Query("select m from Major m where m.abbrev=?2 and m.faculty.abbrev=?1")
    Major findByFacultyAndMajorAbbrevs(String faculty, String major);
    @Query("select m from Major m where m.name=?1 and m.faculty.id=?2")
    Major findByNameAndFacultyId(String name,long id);

}
