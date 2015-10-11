package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.repositories.FacultyRepository;
import pl.kni.repositories.MajorRepository;
import pl.kni.repositories.SemesterRepository;
import pl.kni.repositories.SubjectRepository;
import pl.kni.services.SemesterService;

import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @RequestMapping(value = "/{faculty}/{major}/sem/{number}",method = RequestMethod.GET)
    public String semester(@PathVariable String faculty,
                           @PathVariable String major,
                           @PathVariable int number,
                           Model model){
        try {
            model.addAttribute("subjects", semesterService.getSubjects(faculty, major, number));
        } catch (SemesterNotFoundException e) {
            // TODO: handle error by returning 404 page
            model.addAttribute("error","This semester doesn't exist.");
            return "404";
        }
        return "semester";
    }


}
