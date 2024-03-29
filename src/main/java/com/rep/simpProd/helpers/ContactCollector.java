package com.rep.simpProd.helpers;

import com.rep.simpProd.entity.Contact;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContactCollector {

    @Autowired
    private ContactService contactService;

    public Map<String, String> getAddressInfContrePartie(Contrepartie contrepartie){
        Map<String, String> contactInf = new HashMap<>();
        Contact contact = contactService.getContactByContr(contrepartie);
        contactInf.put("email", contact.getEmail());
        contactInf.put("fullAddress", contact.getFullAddress());
        contactInf.put("coontry", contact.getCountryCode());
        contactInf.put("tel", contact.getTel());
        contactInf.put("postCode", contact.getPostCode());
        return contactInf;
    }

}
