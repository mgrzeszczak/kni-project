package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.forms.MajorCreateForm;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.services.FacultyService;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @ModelAttribute("content")
    public String content(){
        return "faculty";
    }

    @Autowired
    private FacultyService facultyService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String sem(@PathVariable long id, Model model) throws FacultyNotFoundException {
        Faculty faculty = facultyService.findById(id);
        model.addAttribute("faculty",faculty);
        return "index";
    }

}
