package pl.kni.controllers;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.support.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import pl.kni.exceptions.NoteCreationFailedException;
import pl.kni.exceptions.OpinionNotFoundException;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.BookCreateForm;
import pl.kni.forms.OpinionCreateForm;
import pl.kni.models.*;
import pl.kni.services.*;
import pl.kni.validation.BookCreateFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @ModelAttribute("content")
    public String content(){
        return "subject";
    }

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
        setModel(model, id, authentication);
        model.addAttribute("tab", "books");
        return "index";
    }

    @RequestMapping(value = "/{id}/opinion/create",method = RequestMethod.POST)
    public String createOpinion(@Valid @ModelAttribute("opinionCreateForm") OpinionCreateForm opinionCreateForm, BookCreateForm bookCreateForm,
                                BindingResult result, @PathVariable long id, Authentication authentication, Model model) throws SubjectNotFoundException{

        System.out.println(opinionCreateForm.getComment());

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()){
                System.out.println(error.getDefaultMessage());
            }
            setModel(model,id,authentication);
            model.addAttribute("opinionsTab",true);
            return "index";
        }
        opinionService.add(opinionCreateForm, authentication.getName());
        return "redirect:/subject/"+String.valueOf(id)+"?opinionCreateOk#opinions";
    }

    @RequestMapping(value = "/{id}/opinion/delete",method = RequestMethod.POST)
    public String removeOpinion(@RequestParam("opinionId") long opinionId, @PathVariable long id) throws SubjectNotFoundException, OpinionNotFoundException{
        opinionService.remove(opinionId);
        return "redirect:/subject/"+id+"#opinions";
    }

    @RequestMapping(value = "/{id}/note/upload",method = RequestMethod.POST)
    public String uploadNote(@RequestParam("file") MultipartFile file,
                             @PathVariable long id) throws MaxUploadSizeExceededException{
        if (file==null) return "redirect:/error";
        if (file.isEmpty()) return "redirect:/subject/"+id+"?fileUploadError#files";
        try {
            noteService.create(file.getOriginalFilename(), id, file);
        } catch (NoteCreationFailedException e) {
            if (e.cause()== NoteCreationFailedException.Cause.NAME_TAKEN)
            return "redirect:/subject/"+id+"?fileUploadNameTaken#files";
            else return "redirect:/subject/"+id+"?fileUploadError#files";
        }
        return "redirect:/subject/"+id+"?fileUploadOk#files";
    }

    @RequestMapping(value = "/{id}/book/create",method = RequestMethod.POST)
    public String createBook(@Valid @ModelAttribute("bookCreateForm")BookCreateForm bookCreateForm,
                             BindingResult result,OpinionCreateForm opinionCreateForm,
                             @PathVariable long id, Model model, Authentication authentication) throws SubjectNotFoundException {

        System.out.println(bookCreateForm.getAuthor());
        System.out.println(bookCreateForm.getTitle());

        if (result.hasErrors()) {
            for (ObjectError error :result.getAllErrors()){
                System.out.println(error.getDefaultMessage());
            }
            setModel(model, id, authentication);
            model.addAttribute("booksTab",true);
            return "index";
        }
        System.out.println("After if");
        bookService.create(bookCreateForm, id);
        return "redirect:/subject/"+id+"?bookCreateOk#books";
    }

    private void setModel(Model model, long subjectId, Authentication authentication) throws SubjectNotFoundException{
        Subject subject = subjectService.findById(subjectId);
        model.addAttribute("subject", subject);
        model.addAttribute("avgDiff", subjectService.averageDifficulty(subject));
        model.addAttribute("books",subject.getBooks());
        Opinion opinion = opinionService.checkIfOpinionCreated(authentication.getName(),subjectId);
        if (opinion!=null){
            model.addAttribute("opinion",opinion);
        }
    }
}
