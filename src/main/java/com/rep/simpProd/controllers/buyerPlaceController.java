package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class buyerPlaceController {

    @GetMapping("/buyerMain")
    public String getAllContracts() {
        return "buyerPlace/mainPageBuyer";
        //return "usTem/usCreation";
    }

}
