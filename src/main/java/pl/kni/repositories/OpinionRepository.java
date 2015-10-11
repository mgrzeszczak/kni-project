package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Opinion;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface OpinionRepository extends CrudRepository<Opinion,Long> {
}
