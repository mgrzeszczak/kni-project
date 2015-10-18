package pl.kni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kni.exceptions.NoteCreationFailedException;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.OpinionCreateForm;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.models.Subject;
import pl.kni.services.NoteService;
import pl.kni.services.OpinionService;
import pl.kni.services.SubjectService;
import pl.kni.services.TeacherService;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Maciej on 11.10.2015.
 */
@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private OpinionService opinionService;
    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String subject(@PathVariable long id,
                          Model model, OpinionCreateForm opinionCreateForm) throws SubjectNotFoundException{
        Subject subject = subjectService.findById(id);
        model.addAttribute("subject",subject);
        model.addAttribute("avgDiff", subjectService.averageDifficulty(subject));
        return "subject";
    }

    @RequestMapping(value = "/{id}/opinion/create",method = RequestMethod.POST)
    public String createOpinion(@Valid @ModelAttribute("opinionCreateForm") OpinionCreateForm opinionCreateForm,
                                BindingResult result, @PathVariable long id){
        if (result.hasErrors()) return "/subject/"+String.valueOf("id");
        try {
            opinionService.add(opinionCreateForm);
        } catch (SubjectNotFoundException e) {
            e.printStackTrace();
            return "redirect:/subject/"+String.valueOf(id)+"?opinionCreateError";
        }
        return "redirect:/subject/"+String.valueOf(id)+"?opinionCreateOk";
    }

    @RequestMapping(value = "/{id}/note/upload",method = RequestMethod.POST)
    public String uploadNote(@RequestParam("name") String name,
                             @RequestParam("file") MultipartFile file,
                             @PathVariable long id){

        System.out.println(name);
        if (file.isEmpty()) return "redirect:/subject/"+String.valueOf(id)+"?fileUploadError";
        try {
            noteService.create(name,id,file);
        } catch (NoteCreationFailedException e) {
            if (e.cause()== NoteCreationFailedException.Cause.NAME_TAKEN)
            return "redirect:/subject/"+String.valueOf(id)+"?fileUploadNameTaken";
            else return "redirect:/subject/"+String.valueOf(id)+"?fileUploadError";
        }
        return "redirect:/subject/"+String.valueOf(id)+"?fileUploadOk";
    }



    private void writeToHardDrive(MultipartFile file, File outputFile) throws IOException {
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(outputFile));
        stream.write(bytes);
        stream.close();
    }

    private File checkNameAvailability(String name, long subjectId) throws IOException {
        try {
            Subject subject = subjectService.findById(subjectId);
            Semester semester = subject.getSemester();
            Major major = semester.getMajor();
            Faculty faculty = major.getFaculty();
            String folderPath = "E:/Resources/"+faculty.getName()+"/"+major.getName()+"/Semestr "+semester.getNumber()+"/"+subject.getName();
            String filePath = folderPath+"/"+name;
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
                return new File(filePath);
            }
            File file = new File(filePath);
            if (file.exists()) return null;
            return file;
        } catch (SubjectNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
