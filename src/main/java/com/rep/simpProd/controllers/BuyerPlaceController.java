package com.rep.simpProd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyerPlaceController {

    @GetMapping("/buyerMain")
    public String getAllContracts() {
        return "buyerPlace/mainPageBuyer";

    }

}
