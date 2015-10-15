package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.FacultyCreateForm;
import pl.kni.forms.MajorCreateForm;
import pl.kni.forms.SubjectCreateForm;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.repositories.FacultyRepository;
import pl.kni.security.Role;
import pl.kni.services.FacultyService;
import pl.kni.services.MajorService;
import pl.kni.services.SemesterService;
import pl.kni.services.SubjectService;
import pl.kni.validation.FacultyCreateFormValidator;
import pl.kni.validation.MajorCreateFormValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 13.10.2015.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private FacultyCreateFormValidator facultyCreateFormValidator;
    @Autowired
    private MajorCreateFormValidator majorCreateFormValidator;

    @ModelAttribute("faculties")
    public List<Faculty> getFaculties(){
        List<Faculty> faculties = facultyService.all();
        return faculties;
    }
    @ModelAttribute("majors")
    public List<Major> getMajors(){
        List<Faculty> faculties = facultyService.all();
        return faculties.size() != 0 ? faculties.get(0).getMajors() : new ArrayList<Major>();
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String addFaculty(FacultyCreateForm facultyCreateForm, MajorCreateForm majorCreateForm){
        return "admin";
    }

    @InitBinder(value = "facultyCreateForm")
    public void initFacultyBinder(WebDataBinder binder){
        binder.addValidators(facultyCreateFormValidator);
    }
    @InitBinder(value = "majorCreateForm")
    public void initMajorBinder(WebDataBinder binder){
        binder.addValidators(majorCreateFormValidator);
    }

    @RequestMapping(value = "/faculty/create",method = RequestMethod.POST)
    public String addFaculty(@Valid @ModelAttribute("facultyCreateForm") FacultyCreateForm facultyCreateForm,
                             BindingResult result, MajorCreateForm majorCreateForm){
        if (result.hasErrors()){
            return "admin";
        }
        facultyService.create(facultyCreateForm);
        return "redirect:/admin?facultyCreated";
    }

    @RequestMapping(value = "/faculty/remove",method = RequestMethod.POST)
    public String removeFaculty(@RequestParam long id){
        try {
            facultyService.remove(id);
        } catch (FacultyNotFoundException e) {
            return "redirect:/admin?facultyDeleteError";
        }
        return "redirect:/admin?facultyDeleteOk";
    }

    @RequestMapping(value = "/major/create",method = RequestMethod.POST)
    public String addMajor(@Valid @ModelAttribute("majorCreateForm")MajorCreateForm majorCreateForm, BindingResult result, FacultyCreateForm form){
        if (result.hasErrors()){
            return "admin";
        }
        try {
            majorService.create(majorCreateForm);
        } catch (FacultyNotFoundException e) {
            e.printStackTrace();
            return "redirect:/admin?majorCreateError";
        }
        return "redirect:/admin?majorCreateOk";
    }

    @RequestMapping(value = "/major/remove",method = RequestMethod.POST)
    public String removeMajor(@RequestParam long id){
        try {
            majorService.remove(id);
        } catch (MajorNotFoundException e) {
            return "redirect:/admin?majorDeleteError";
        }
        return "redirect:/admin?majorDeleteOk";
    }

    @RequestMapping(value = "/semester/create",method = RequestMethod.POST)
    public String createSemesters(@RequestParam long majorId, @RequestParam int amount){
        try {
            semesterService.create(majorId, amount);
        } catch (MajorNotFoundException e) {
            return "redirect:/admin?semesterCreateError";
        }
        return "redirect:/admin?semesterCreateOk";
    }

    @RequestMapping(value = "/semester/remove",method = RequestMethod.POST)
    public String removeSemesters(@RequestParam List<Long> ids, @RequestParam long majorId){
        semesterService.remove(ids);
        return "redirect:/admin?semesterRemoveOk";
    }

    /***************************************************/
    /********* ADMIN REST API FOR DYNAMIC FORMS ********/
    /***************************************************/
    @RequestMapping(value = "/majors/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Major> getMajors(@RequestParam long facultyId){
        try {
            Faculty f = facultyService.findById(facultyId);
            return f.getMajors();
        } catch (FacultyNotFoundException e) {
            return new ArrayList<Major>();
        }
    }
    @RequestMapping(value = "/semesters/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Semester> getSemesters(@RequestParam long majorId){
        try {
            Major major = majorService.findById(majorId);
            for (Semester semester : major.getSemesters()){
                System.out.println(semester.getId());
            }
            return major.getSemesters();
        } catch (MajorNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
