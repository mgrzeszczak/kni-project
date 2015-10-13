package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.RememberMeToken;

/**
 * Created by Maciej on 13.10.2015.
 */
public interface RememberMeTokenRepository extends CrudRepository<RememberMeToken,Long> {

    RememberMeToken findBySeries(String series);
    void deleteByUsername(String username);

}
