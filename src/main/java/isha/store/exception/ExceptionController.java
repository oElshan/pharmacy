package isha.store.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController  {
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
//        return new ModelAndView("error");
//    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request, Exception e)   {
        System.out.println( "Request: " + request.getRequestURL() + " raised " + e);
        return "404";
    }

//    @ExceptionHandler(Exception.class)
//    public String handleException(HttpServletRequest request, Exception e)   {
//        System.out.println( "Request: " + request.getRequestURL() + " raised " + e);
//        return "404";
//    }

//    @ExceptionHandler(RuntimeException.class)
//    public String handleRuntimeException(HttpServletRequest request, Exception e)   {
//        System.out.println( "Request: " + request.getRequestURL() + " raised " + e);
//        return "404";
//    }
}
