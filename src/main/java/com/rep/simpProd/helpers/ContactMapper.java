package com.rep.simpProd.helpers;

import com.rep.simpProd.entity.Contact;
import org.modelmapper.ModelMapper;

public class ContactMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Contact convertToContactEntity(ContactInfo contactInfo){
        return modelMapper.map(contactInfo, Contact.class);
    }

    public static ContactInfo covertToContactInfo(Contact contact){
        return modelMapper.map(contact, ContactInfo.class);
    }

}
