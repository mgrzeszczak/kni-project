package pl.kni.controllers;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import pl.kni.exceptions.NoteCreationFailedException;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.BookCreateForm;
import pl.kni.forms.OpinionCreateForm;
import pl.kni.models.*;
import pl.kni.services.*;
import pl.kni.validation.BookCreateFormValidator;

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
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCreateFormValidator bookCreateFormValidator;

    private final Logger logger = Logger.getLogger(this.getClass());

    @InitBinder(value = "bookCreateForm")
    public void initFacultyBinder(WebDataBinder binder){
        binder.addValidators(bookCreateFormValidator);
    }

    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    public String handleMultipartException(MultipartException e){
        logger.error(e.getMessage());
        return "redirect:/html/errors/400.html";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String subject(@PathVariable long id,
                          Model model,
                          OpinionCreateForm opinionCreateForm,
                          BookCreateForm bookCreateForm,
                          Authentication authentication) throws SubjectNotFoundException{
        Subject subject = subjectService.findById(id);
        model.addAttribute("subject", subject);
        model.addAttribute("avgDiff", subjectService.averageDifficulty(subject));
        model.addAttribute("books",subject.getBooks());
        Opinion opinion = opinionService.checkIfOpinionCreated(authentication.getName(),id);
        if (opinion!=null){
            model.addAttribute("opinion",opinion);
        }
        return "subject";
    }

    @RequestMapping(value = "/{id}/opinion/create",method = RequestMethod.POST)
    public String createOpinion(@Valid @ModelAttribute("opinionCreateForm") OpinionCreateForm opinionCreateForm,
                                BindingResult result, @PathVariable long id, Authentication authentication){
        if (result.hasErrors()) return "redirect:/subject/"+id+"?opinionCreateError";
        try {
            opinionService.add(opinionCreateForm,authentication.getName());
        } catch (SubjectNotFoundException e) {
            e.printStackTrace();
            return "redirect:/subject/"+String.valueOf(id)+"?opinionCreateError";
        }
        return "redirect:/subject/"+String.valueOf(id)+"?opinionCreateOk";
    }

    @RequestMapping(value = "/{id}/note/upload",method = RequestMethod.POST)
    public String uploadNote(@RequestParam("file") MultipartFile file,
                             @PathVariable long id) throws MaxUploadSizeExceededException{
        if (file.isEmpty()) return "redirect:/subject/"+id+"?fileUploadError";
        try {
            noteService.create(file.getOriginalFilename(), id, file);
        } catch (NoteCreationFailedException e) {
            if (e.cause()== NoteCreationFailedException.Cause.NAME_TAKEN)
            return "redirect:/subject/"+id+"?fileUploadNameTaken";
            else return "redirect:/subject/"+id+"?fileUploadError";
        }
        return "redirect:/subject/"+id+"?fileUploadOk";
    }

    @RequestMapping(value = "/{id}/book/create",method = RequestMethod.POST)
    public String createBook(@Valid @ModelAttribute("bookCreateForm")BookCreateForm bookCreateForm,
                             BindingResult result,
                             @PathVariable long id) {
        if (result.hasErrors()) return "redirect:/subject/"+id+"?bookCreateExists";
        try {
            bookService.create(bookCreateForm, id);
        } catch (SubjectNotFoundException e) {
            e.printStackTrace();
            return "redirect:/subject/"+id+"?bookCreateInvalid";
        }
        return "redirect:/subject/"+id+"?bookCreateOk";
        // Subject subject = subjectService.findById(id);
        // model.addAttribute("subject",subject);
        // return "subject";
    }
}
