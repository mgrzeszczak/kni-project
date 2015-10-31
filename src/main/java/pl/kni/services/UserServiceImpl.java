package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.UserNotFoundException;
import pl.kni.forms.UserCreateForm;
import pl.kni.models.User;
import pl.kni.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maciej on 12.10.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;



    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        User user =  userRepository.findOneByEmail(email);
        if (user == null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public User findById(long id) throws UserNotFoundException {
        User user = userRepository.findOne(id);
        if (user==null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public List<User> findAll() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public User create(UserCreateForm userCreateForm) {
        User user = new User(userCreateForm);
        return userRepository.save(user);
    }

    @Override
    public boolean emailAvailable(String email) {
        User user = userRepository.findOneByEmail(email);
        return user==null? true : false;
    }


}
