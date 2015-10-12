package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.User;

/**
 * Created by Maciej on 12.10.2015.
 */
public interface UserRepository extends CrudRepository<User,Long>{

    User findOneByEmail(String email);

}
