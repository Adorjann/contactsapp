package com.adorjanpraksa.contactsapp.web.mapper;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.service.ContactTypeService;
import com.adorjanpraksa.contactsapp.service.impl.ContactService;
import com.adorjanpraksa.contactsapp.service.impl.UserProfileService;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.LongToDoubleFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ContactMapper {

    private final ContactTypeService contactTypeService;
    private final ContactService contactService;
    private final UserProfileService userProfileService;

    public Optional<Contact> mapToEntity(ContactDto dto, String userEmail) {

        var userProfileOptional = userProfileService.findByEmail(userEmail);
        var contactTypeOptional = contactTypeService.findByTypeName(dto.getContactTypeName());

        if (contactTypeOptional.isEmpty()){
            return Optional.empty();
        }

        var contact = new Contact();

        contact.setContactType(contactTypeOptional.get());
        contact.setUserProfile(userProfileOptional.get());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setAddress(dto.getAddress());
        contact.setPhoneNumber(dto.getPhoneNumber());

        return Optional.of(contact);
    }
    public Optional<Contact> mapToEntity(ContactDto dto, String userEmail, Long contactId ) {

        var userProfileOptional = userProfileService.findByEmail(userEmail);
        var contactTypeOptional = contactTypeService.findByTypeName(dto.getContactTypeName());

        if (contactTypeOptional.isEmpty()){
            return Optional.empty();
        }

        var contactOptional = contactService.findById(contactId);
        if (contactOptional.isEmpty()){
            return Optional.empty();
        }
        var contact = contactOptional.get();
        contact.setContactType(contactTypeOptional.get());
        contact.setUserProfile(userProfileOptional.get());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setAddress(dto.getAddress());
        contact.setPhoneNumber(dto.getPhoneNumber());

        return Optional.of(contact);
    }

    public ContactDto mapToDto(Contact contact) {

        var dto = new ContactDto();
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setAddress(contact.getAddress());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setContactTypeName(contact.getContactType().getType());

        return dto;
    }

    public List<ContactDto> mapToDto(List<Contact> contacts){
        return contacts.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
