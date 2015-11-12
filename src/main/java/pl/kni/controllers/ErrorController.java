package pl.kni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maciej on 03.11.2015.
 */
@Controller
public class ErrorController {

    @RequestMapping("/404")
    private String notFound(Model model){
        model.addAttribute("error",404);
        model.addAttribute("message","Not found.");
        return "errors";
    }
    @RequestMapping("/500")
    private String internal(Model model){
        model.addAttribute("error",500);
        model.addAttribute("message","Internal error.");
        return "errors";
    }
    @RequestMapping("/403")
    private String notAuthorized(Model model){
        model.addAttribute("error",403);
        model.addAttribute("message","You are not authorized to access this site.");
        return "errors";
    }
    @RequestMapping("/400")
    private String badRequest(Model model){
        model.addAttribute("error",400);
        model.addAttribute("message","Invalid request.");
        return "errors";
    }

}
