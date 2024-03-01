package com.rep.simpProd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/mainSimp")
    public String showIntro() {
        return "mainPage";
    }

    @GetMapping("/generalView")
    public String showGeneralPlace() {
        return "buyerPlace/mainPageBuyer";
    }

}
