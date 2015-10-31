package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kni.exceptions.TeacherNotFoundException;
import pl.kni.models.Teacher;
import pl.kni.services.TeacherService;

import java.util.Optional;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @ModelAttribute("content")
    public String content(){
        return "teacher";
    }
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/{id}")
    public String getTeacher(@PathVariable long id, Model model) throws TeacherNotFoundException{
        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isPresent()) model.addAttribute("teacher",teacher.get());
        else throw new TeacherNotFoundException();
        return "teacher";
    }

}
