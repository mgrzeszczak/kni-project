package pl.kni.advice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.kni.security.Role;

/**
 * Created by Maciej on 12.10.2015.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUesr(Authentication authentication){
        return (authentication==null)? null : (UserDetails)authentication.getPrincipal();
    }

    @ModelAttribute("admin")
    public Role getAdminRole(){
        return Role.ADMIN;
    }

}
