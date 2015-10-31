package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/semester")
public class SemesterController {

    @ModelAttribute("content")
    public String content(){
        return "semester";
    }

    @Autowired
    private SemesterService semesterService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String sem(@PathVariable long id, Model model) throws SemesterNotFoundException{
        Semester semester = semesterService.findById(id);
        model.addAttribute("semester",semester);
        return "index";
    }


}
