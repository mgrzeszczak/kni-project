package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.repositories.FacultyRepository;
import pl.kni.services.MajorService;

import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
public class MajorController {

    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/{faculty}/{major}",method = RequestMethod.GET)
    public String major(@PathVariable String faculty,
                        @PathVariable String major,
                        Model model){
        try {
            model.addAttribute("semesters",majorService.getSemesters(faculty,major));
        } catch (MajorNotFoundException e) {
            // TODO: handle exception by returning error page;
            model.addAttribute("error","This major doesn't exist.");
            return "404";
        }
        return "major";
    }
}
