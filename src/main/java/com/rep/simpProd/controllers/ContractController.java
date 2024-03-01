package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Contract;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.services.ContractService;
import com.rep.simpProd.services.ContrepartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.GetExchange;

import java.util.*;

@Controller
public class ContractController {

    private final ContrepartieService contrepartieService;
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService, ContrepartieService contrepartieService){

        this.contractService = contractService;
        this.contrepartieService = contrepartieService;

    }

    @GetMapping("/contracts")
    public String getAllContracts(Model model) {
        List<Contract> contracts = contractService.getAllContracts();
        model.addAttribute("contracts", contracts);
        return "contract/contractsList";
    }

    @GetMapping("/contract/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Contract contract = contractService.getContractById(id).orElse(null);
        model.addAttribute("contract", contract);
        return "contract/contractDetails";
    }

    @GetMapping("/contract/edit/{id}")
    public String editContract(@PathVariable Long id, Model model) {
        Contract contract = contractService.getContractById(id).orElse(null);
        List<Contrepartie> contreparties = new ArrayList<>();
        contreparties.add(contract.getContrepartie());
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("contract", contract);
        attributes.put("contreparties", contreparties);
        model.addAllAttributes(attributes);
        return "contract/creationContract";
    }

    @GetMapping("/contract/delete/{id}")
    public String deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return "redirect:/contracts";
    }

    @GetMapping("/newContract")
    public String createNewContract(Model model){
        List<Contrepartie> contreparties = contrepartieService.getAllContres();
        Contract contract = new Contract();
        contract.setDateDebut(new Date());
        contract.setIsDone(false);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("contract", contract);
        attributes.put("contreparties", contreparties);
        model.addAllAttributes(attributes);
        return "contract/creationContract";
    }

    @PostMapping("/saveContract")
    public String submitPartner(@ModelAttribute Contract contract) {
        contractService.createContract(contract);
        return "redirect:/contreparties";
    }

}
