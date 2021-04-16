package ru.geekbrains.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainAdminController {

    @Secured({"ADMIN"})
    @GetMapping("/admin")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin_main_page";
    }




}