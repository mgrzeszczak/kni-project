package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kni.forms.FacultyCreateForm;
import pl.kni.models.Major;
import pl.kni.repositories.FacultyRepository;
import pl.kni.repositories.MajorRepository;
import pl.kni.services.FacultyService;
import pl.kni.services.SemesterService;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
public class IndexController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private FacultyRepository facultyRepository;

    @ModelAttribute("content")
    public String content(){
        return "main";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(FacultyCreateForm facultyCreateForm, Model model){
        model.addAttribute("faculties",facultyService.all());
        return "index";
    }
}
