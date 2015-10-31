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
    public String register(@Valid @ModelAttribute("userCreateForm") UserCreateForm userCreateForm, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "index";
        }
        userService.create(userCreateForm);
        return "redirect:/login";
    }

}
