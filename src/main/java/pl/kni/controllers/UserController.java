package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kni.forms.UserCreateForm;
import pl.kni.services.UserService;
import pl.kni.validation.UserCreateFormValidator;

import javax.validation.Valid;

/**
 * Created by Maciej on 12.10.2015.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @InitBinder()
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegisterPage(UserCreateForm userCreateForm){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@Valid UserCreateForm userCreateForm, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "register";
        }
        userService.create(userCreateForm);
        return "redirect:/login";
    }

}
