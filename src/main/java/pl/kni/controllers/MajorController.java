package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.repositories.FacultyRepository;
import pl.kni.services.MajorService;
import pl.kni.services.SemesterService;

import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String sem(@PathVariable long id, Model model) throws MajorNotFoundException{
        Major major = majorService.findById(id);
        model.addAttribute("major",major);
        return "major";
    }
}
