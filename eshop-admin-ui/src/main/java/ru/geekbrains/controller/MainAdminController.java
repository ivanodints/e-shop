package ru.geekbrains.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainAdminController {

    @Secured({"ADMIN"})
    @RequestMapping("/admin")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin_main_page";
    }




}