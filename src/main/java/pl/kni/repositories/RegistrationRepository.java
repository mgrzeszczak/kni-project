package pl.kni.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kni.models.Registration;

import java.util.Optional;

/**
 * Created by Maciej on 16.11.2015.
 */
public interface RegistrationRepository extends CrudRepository<Registration,Long> {

    Registration findByEmailAndToken(String email, String token);
    Registration findOneByEmail(String email);

}
