package pl.kni.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Opinion;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface OpinionRepository extends CrudRepository<Opinion,Long> {

    @Query("select o from Opinion o where o.username =?1 and o.subject.id=?2")
    Opinion findOpinionByUsernameAndSubjectId(String username,long subjectId);

}
