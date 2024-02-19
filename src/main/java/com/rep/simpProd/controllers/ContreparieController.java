package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.entity.Product;
import com.rep.simpProd.enums.TypePartenaire;
import com.rep.simpProd.services.ContrepartieService;
import com.rep.simpProd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContreparieController {

    private final ContrepartieService contrepartieService;

    @Autowired
    public ContreparieController(ContrepartieService contrepartieService){
        this.contrepartieService = contrepartieService;
    }

    @GetMapping("/contreparties")
    public String getAllContres(Model model) {
        List<Contrepartie> contreparties = contrepartieService.getAllContres();
        model.addAttribute("contreparties", contreparties);
        return "contrepartie/ContrepartieList";
    }

    @GetMapping("/contrepartie/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Contrepartie contrepartie = contrepartieService.getContrById(id).orElse(null);
        model.addAttribute("contrepartie", contrepartie);
        return "contrepartie/contrDet";
    }

    @GetMapping("/contrepartie/edit/{id}")
    public String showEditContr(@PathVariable Long id, Model model) {
        Contrepartie contrepartie = contrepartieService.getContrById(id).orElse(null);
        model.addAttribute("contrepartie", contrepartie);
        return "contrepartie/ContrepartieCreation";
    }

    @GetMapping("/contrepartie/delete/{id}")
    public String deleteContrepartie(@PathVariable Long id) {
        contrepartieService.deleteContr(id);
        return "redirect:/contreparties";
    }

    @GetMapping("/newContr")
    public String createNewContr(Model model){
        model.addAttribute("contrepartie", new Contrepartie());
        return "contrepartie/ContrepartieCreation";
    }

    @PostMapping("/saveContr")
    public String submitPartner(@ModelAttribute Contrepartie contrepartie) {
        contrepartieService.createContrePartie(contrepartie);
        return "redirect:/contreparties";
    }

}
