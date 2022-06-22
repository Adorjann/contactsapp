package com.adorjanpraksa.contactsapp.web.dtoConverter;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactToContactDto implements Converter<Contact, ContactDto> {

    @Override
    public ContactDto convert(Contact contact) {
        ContactDto dto = new ContactDto();

        dto.setId(contact.getId());
        if (contact.getFirstName() != null){
            dto.setFirstName(contact.getFirstName());
        }
        if (contact.getLastName() != null){
            dto.setLastName(contact.getLastName());
        }
        if (contact.getAddress() != null){
            dto.setAddress(contact.getAddress());
        }
        if (contact.getPhoneNumber() != null){
            dto.setPhoneNumber(contact.getPhoneNumber());
        }
        if (contact.getContactType() != null){
            dto.setContactTypeId(contact.getContactType().getId());
        }
        if (contact.getUserProfile() != null){
            dto.setUserProfileId(contact.getUserProfile().getId());
        }

        return dto;

    }

    public List<ContactDto> convert(List<Contact> contacts){
        return contacts.stream().map(this::convert).collect(Collectors.toList());
    }

}
