package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.enums.TypePartenaire;
import com.rep.simpProd.services.ContrepartieService;
import com.rep.simpProd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContreparieController {

    private final ContrepartieService contrepartieService;

    @Autowired
    public ContreparieController(ContrepartieService contrepartieService){
        this.contrepartieService = contrepartieService;
    }

    @GetMapping("/newContr")
    public String createNewContr(Model model){
        model.addAttribute("contrepartie", new Contrepartie());
        return "ContrepartieCreation";
    }

    @PostMapping("/saveContr")
    public String submitPartner(@ModelAttribute Contrepartie contrepartie) {
        contrepartieService.createContrePartie(contrepartie);
        return "ok";
    }

}
