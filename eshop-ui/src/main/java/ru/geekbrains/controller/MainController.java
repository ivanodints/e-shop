package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/plague-brush")
    public String indexPage(Model model, HttpSession httpSession) {
        return "index";
    }
}