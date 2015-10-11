package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.models.Faculty;
import pl.kni.services.FacultyService;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @RequestMapping(value = "/{faculty}",method = RequestMethod.GET)
    public String faculty(@PathVariable String faculty, Model model){
        try {
            model.addAttribute("majors",facultyService.getMajors(faculty));
        } catch (FacultyNotFoundException e) {
            // TODO: handle exception by returning error page
            model.addAttribute("error","This faculty doesn't exist.");
            return "404";
        }
        return "faculty";
    }

}
