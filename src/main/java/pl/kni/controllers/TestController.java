package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kni.models.User;
import pl.kni.repositories.UserRepository;
import pl.kni.security.Role;
import pl.kni.services.UserService;

/**
 * Created by Maciej on 13.10.2015.
 */
@Controller
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/rest")
    @ResponseBody
    public String admin(){
        return "test";
    }
}
