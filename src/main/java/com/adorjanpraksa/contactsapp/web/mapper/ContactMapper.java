package com.adorjanpraksa.contactsapp.web.mapper;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Component
public class ContactMapper {

    public Contact mapToEntity(ContactDto dto) {

        var contact = new Contact();

        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setAddress(dto.getAddress());
        contact.setPhoneNumber(dto.getPhoneNumber());

        return contact;
    }

    public Contact mapToEntity(Contact contact, ContactDto contactDto) {

        if (nonNull(contactDto.getFirstName())) {
            contact.setFirstName(contactDto.getFirstName());
        }
        if (nonNull(contactDto.getLastName())) {
            contact.setLastName(contactDto.getLastName());
        }
        if (nonNull(contactDto.getAddress())) {
            contact.setAddress(contactDto.getAddress());
        }
        if (nonNull(contactDto.getPhoneNumber())) {
            contact.setPhoneNumber(contactDto.getPhoneNumber());
        }

        return contact;
    }


    public ContactDto mapToDto(Contact contact) {

        var dto = new ContactDto();
        dto.setId(contact.getId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setAddress(contact.getAddress());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setContactTypeName(contact.getContactType().getType());

        return dto;
    }

    public List<ContactDto> mapToDto(List<Contact> contacts) {

        return contacts.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
