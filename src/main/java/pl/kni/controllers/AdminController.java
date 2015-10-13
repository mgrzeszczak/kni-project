package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.forms.FacultyCreateForm;
import pl.kni.services.FacultyService;
import javax.validation.Valid;

/**
 * Created by Maciej on 13.10.2015.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FacultyService facultyService;

    @RequestMapping(value = "/faculty",method = RequestMethod.GET)
    public String addFaculty(FacultyCreateForm facultyCreateForm){
        return "admin";
    }

    @RequestMapping(value = "/faculty/create",method = RequestMethod.POST)
    public String addFaculty(@Valid FacultyCreateForm facultyCreateForm, BindingResult result){
        if (result.hasErrors()){
            return "/admin";
        }
        facultyService.create(facultyCreateForm);
        return "redirect:/"+facultyCreateForm.getAbbrev();
    }

    @RequestMapping(value = "/faculty/remove",method = RequestMethod.POST)
    public String removeFaculty(@RequestParam long id){
        try {
            facultyService.remove(id);
        } catch (FacultyNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }



}
