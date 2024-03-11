package com.rep.simpProd.controllers;

import com.rep.simpProd.entity.Contact;
import com.rep.simpProd.entity.Contract;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.helpers.ContactInfo;
import com.rep.simpProd.helpers.ContactMapper;
import com.rep.simpProd.services.ContactService;
import com.rep.simpProd.services.ContrepartieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    private final ContrepartieService contrepartieService;
    private final ContactService contactService;

    private List<Contrepartie> contrepartieList;

    @Autowired
    public ContactController(ContrepartieService contrepartieService, ContactService contactService) {
        this.contrepartieService = contrepartieService;
        this.contactService = contactService;
    }

    @GetMapping("/newContact")
    public String addNewContact(ContactInfo contactInfo, Model model){
        //List<Contrepartie> contrepartieList = contrepartieService.getAllContres();
        contrepartieList = contrepartieService.getAllContres();
        model.addAttribute("contreparties", contrepartieList);
        //model.addAttribute("contactInf", new ContactInfo());
        return "contactInfo/creationContact";
    }

    @PostMapping("/newContact")
    public String saveNewContact(@Valid ContactInfo contactInfo, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("contreparties", contactInfo.getIdContr());
            return "contactInfo/creationContact";
        }else {
            Contact contact = ContactMapper.convertToContactEntity(contactInfo);
            contactService.createNewContact(contact);
            return "redirect:/contactsAll";
        }


    }


    @GetMapping("/contactsAll")
    public String getAllContracts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "contactInfo/contactsList";
    }

    @GetMapping("/contact/{id}")
    public String ContactById(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id).orElse(null);
        model.addAttribute("contact", contact);
        return "contactInfo/contactDetails";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contactsAll";
    }


    @GetMapping("/contact/edit/{id}")
    public String editContact(@PathVariable Long id, Model model){
        Contact c = contactService.getContactById(id).orElse(null);
        ContactInfo contactInfo = ContactMapper.covertToContactInfo(c);
        model.addAttribute("contactInfo", contactInfo);
        return "contactInfo/editContact";
    }

    @PostMapping("/editContact")
    public String checkSaveEditContact(@Valid ContactInfo contactInfo, Errors errors, Model model){

        if (errors.hasErrors()){
            return "contactInfo/editContact";
        }else {
            Contact contact = ContactMapper.convertToContactEntity(contactInfo);
            contactService.updateContact(contact.getId(), contact);
            return "redirect:/contactsAll";
        }


    }


}
