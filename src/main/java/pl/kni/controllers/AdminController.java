package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.kni.exceptions.*;
import pl.kni.forms.FacultyCreateForm;
import pl.kni.forms.MajorCreateForm;
import pl.kni.forms.SubjectCreateForm;
import pl.kni.forms.TeacherCreateForm;
import pl.kni.models.*;
import pl.kni.repositories.FacultyRepository;
import pl.kni.security.Role;
import pl.kni.services.*;
import pl.kni.validation.FacultyCreateFormValidator;
import pl.kni.validation.MajorCreateFormValidator;
import pl.kni.validation.SubjectCreateFormValidator;
import pl.kni.validation.TeacherCreateFormValidator;

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
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private FacultyCreateFormValidator facultyCreateFormValidator;
    @Autowired
    private MajorCreateFormValidator majorCreateFormValidator;
    @Autowired
    private SubjectCreateFormValidator subjectCreateFormValidator;
    @Autowired
    private TeacherCreateFormValidator teacherCreateFormValidator;

    @ModelAttribute("faculties")
    public List<Faculty> getFaculties(){
        return facultyService.all();
    }
    @ModelAttribute("majors")
    public List<Major> getMajors(){
        List<Faculty> faculties = facultyService.all();
        return faculties.size() != 0 ? faculties.get(0).getMajors() : new ArrayList<Major>();
    }
    @ModelAttribute("semesters")
    public List<Semester> getSemesters(){
        List<Major> majors = getMajors();
        return majors.size()==0? new ArrayList<>() : majors.get(0).getSemesters();
    }
    @ModelAttribute("subjects")
    public List<Subject> getSubjects(){
        List<Semester> list = getSemesters();
        if (list.size()==0) return new ArrayList<>();
        return list.get(0).getSubjects();
    }
    @ModelAttribute("teachers")
    public List<Teacher> getTeachers(){
        return teacherService.all();
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String addFaculty(FacultyCreateForm facultyCreateForm,
                             MajorCreateForm majorCreateForm,
                             SubjectCreateForm subjectCreateForm,
                             TeacherCreateForm teacherCreateForm){
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
    @InitBinder(value = "subjectCreateForm")
    public void initSubjectBinder(WebDataBinder binder){
        binder.addValidators(subjectCreateFormValidator);
    }
    @InitBinder(value = "teacherCreateForm")
    public void initTeacherBinder(WebDataBinder binder){
        binder.addValidators(teacherCreateFormValidator);
    }

    @RequestMapping(value = "/faculty/create",method = RequestMethod.POST)
    public String addFaculty(@Valid @ModelAttribute("facultyCreateForm") FacultyCreateForm facultyCreateForm,
                             BindingResult result,
                             MajorCreateForm majorCreateForm,
                             SubjectCreateForm subjectCreateForm,
                             TeacherCreateForm teacherCreateForm){
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
    public String addMajor(@Valid @ModelAttribute("majorCreateForm")MajorCreateForm majorCreateForm,
                           BindingResult result,
                           FacultyCreateForm form,
                           SubjectCreateForm subjectCreateForm,
                           TeacherCreateForm teacherCreateForm){
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

    @RequestMapping(value = "/subject/create",method = RequestMethod.POST)
    public String createSubject(@Valid @ModelAttribute("subjectCreateForm")SubjectCreateForm subjectCreateForm,
                                BindingResult result,
                                FacultyCreateForm facultyCreateForm,
                                MajorCreateForm majorCreateForm,
                                TeacherCreateForm teacherCreateForm){
        if (result.hasErrors()) return "admin";
        try {
            subjectService.create(subjectCreateForm);
        } catch (SemesterNotFoundException e) {
            return "redirect:/admin?subjectCreateError";
        }
        return "redirect:/admin?subjectCreateOk";
    }
    @RequestMapping(value = "/subject/remove",method = RequestMethod.POST)
    public String removeSemesters(@RequestParam long id){
        try {
            subjectService.remove(id);
        } catch (SubjectNotFoundException e) {
            e.printStackTrace();
            return "redirect:/admin?subjectRemoveError";
        }
        return "redirect:/admin?subjectRemoveOk";
    }

    @RequestMapping(value = "/teacher/create",method = RequestMethod.POST)
    public String createTeacher(@Valid @ModelAttribute("teacherCreateForm") TeacherCreateForm teacherCreateForm,
                                BindingResult result,
                                FacultyCreateForm facultyCreateForm,
                                MajorCreateForm majorCreateForm,
                                SubjectCreateForm subjectCreateForm){
        if (result.hasErrors()) return "admin";
        teacherService.create(teacherCreateForm);
        return "redirect:/admin?teacherCreateOk";
    }
    @RequestMapping(value = "/teacher/remove",method = RequestMethod.POST)
    public String removeTeacher(@RequestParam long id){
        try {
            teacherService.remove(id);
        } catch (TeacherNotFoundException e) {
            e.printStackTrace();
            return "redirect:/admin?teacherRemoveError";
        }
        return "redirect:/admin?teacherRemoveOk";
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
            return new ArrayList<>();
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
    @RequestMapping(value = "/subjects/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Subject> getSubjects(@RequestParam long semesterId){
        try {
            Semester semester = semesterService.findById(semesterId);
            return semester.getSubjects();
        } catch (SemesterNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
