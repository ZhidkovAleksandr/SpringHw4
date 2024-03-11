package com.rep.simpProd.services;

import com.rep.simpProd.entity.Contact;
import com.rep.simpProd.entity.Contract;
import com.rep.simpProd.entity.Contrepartie;
import com.rep.simpProd.interfaces.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id){
        return contactRepository.findById(id);
    }

    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }

    public Contact createNewContact(Contact contact){
        return contactRepository.save(contact);
    }

    public Contact updateContact (Long id, Contact updatedContact) {
        Optional<Contact> existingContactOptional = contactRepository.findById(id);

        if (existingContactOptional.isPresent()) {
            Contact existingContact = existingContactOptional.get();
            existingContact.setCountryCode(updatedContact.getCountryCode());
            existingContact.setFullAddress(updatedContact.getFullAddress());
            existingContact.setTel(updatedContact.getTel());
            existingContact.setPostCode(updatedContact.getPostCode());
            existingContact.setEmail(updatedContact.getEmail());
            return contactRepository.save(existingContact);
        } else {

            return null;
        }
    }

    public Contact getContactByContr(Contrepartie contrepartie){
        return contactRepository.findByIdContr(contrepartie);
    }

}
