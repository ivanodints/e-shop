package ru.geekbrains.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.errors.NotFoundException;

@Controller
//@RequestMapping("/admin")
public class AuthorizationAdminController {

//    @GetMapping("/authorization")
@GetMapping("/login")
    public String signForm() {
        return "authorization";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;

    }
}
