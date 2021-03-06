package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kni.models.Registration;
import pl.kni.services.EmailService;
import pl.kni.services.TokenService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Maciej on 12.10.2015.
 */
@Controller
public class LoginController {

    @ModelAttribute("content")
    public String content(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Authentication authentication){
        if (authentication!=null) return "redirect:/";
        return "index";
    }

}
