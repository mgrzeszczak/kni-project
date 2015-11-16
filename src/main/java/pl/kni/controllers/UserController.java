package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.kni.forms.UserCreateForm;
import pl.kni.models.User;
import pl.kni.services.RegistrationService;
import pl.kni.services.UserService;
import pl.kni.validation.UserCreateFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * Created by Maciej on 12.10.2015.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;

    @ModelAttribute("content")
    public String content(){
        return "register";
    }

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @InitBinder(value = "userCreateForm")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegisterPage(UserCreateForm userCreateForm){
        return "index";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("userCreateForm") UserCreateForm userCreateForm, BindingResult result, Model model,HttpServletRequest request){
        if (result.hasErrors()) {
            return "index";
        }
        String requestURL = request.getRequestURL().toString();
        String baseURL = requestURL.substring(0,requestURL.indexOf(request.getContextPath())+request.getContextPath().length());
        //userService.create(userCreateForm);
        registrationService.create(userCreateForm,baseURL);
        return "redirect:/login?confirmEmail";
    }

    @RequestMapping(value = "/confirm",method = RequestMethod.GET)
    public String confirm(@RequestParam String email, @RequestParam String token){
        User user = registrationService.confirm(token, email);
        if (user==null) return "redirect:/login?confirmationFail";
        return "redirect:/login?confirmationSuccess";
    }

}
