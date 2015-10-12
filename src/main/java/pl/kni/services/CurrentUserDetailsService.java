package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.UserNotFoundException;
import pl.kni.models.User;
import pl.kni.security.CurrentUser;

/**
 * Created by Maciej on 12.10.2015.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.findByEmail(email);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(String.format("User with email=%s was not found", email));
        }
        return new CurrentUser(user);
    }
}
