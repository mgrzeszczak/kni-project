package pl.kni.controllers;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Maciej on 03.11.2015.
 */
@RestController
public class MyErrorController implements ErrorController{

    @RequestMapping("/error")
    public String error(HttpServletRequest aRequest){
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public String handleMultipartException(){
        return "multipart";
    }





}
