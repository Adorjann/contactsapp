package com.adorjanpraksa.contactsapp.service.impl;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.repository.ContactRepository;
import com.adorjanpraksa.contactsapp.service.ContactTypeService;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository  repository;
    private final ContactTypeService contactTypeService;

    public List<Contact> findAllUsersContacts(String email) {

        return repository.findByUserProfileEmail(email);
    }

    public Optional<Contact> findById(Long id) {

        return repository.findById(id);
    }

    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    public Contact update(Contact contact) {

        return repository.save(contact);
    }

    public Optional<Contact> delete(Long id) {

        var contact = findById(id);

        contact.ifPresent(repository::delete);
        return contact;
    }

    public List<ContactDto> toContactDto(List<Contact> contacts) {
        return contacts.stream().map(this::toContactDto).collect(Collectors.toList());
    }

    public ContactDto toContactDto(Contact contact) {

        ContactDto dto = new ContactDto();
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setAddress(contact.getAddress());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setContactTypeName(contact.getContactType().getType());
        return dto;
    }

    public Optional<Contact> toContact(ContactDto dto) {

        var contactTypeOptional = contactTypeService.findByTypeName(dto.getContactTypeName());
        if (contactTypeOptional.isPresent()){

            var contactOptional = findContactFromDto(dto);
            var contact         = contactOptional.orElseGet(Contact::new);

            contact.setContactType(contactTypeOptional.get());
            contact.setFirstName(dto.getFirstName());
            contact.setLastName(dto.getLastName());
            contact.setAddress(dto.getAddress());
            contact.setPhoneNumber(contact.getPhoneNumber());

            return Optional.of(contact);
        }
        return Optional.empty();
    }

    private Optional<Contact> findContactFromDto(ContactDto dto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }
        else {
            username = principal.toString();
        }
        return searchByParams(dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getPhoneNumber(), dto.getContactTypeName(), username).stream().findFirst();
    }

    public List<Contact> searchByParams(String firstName, String lastName, String address, String phoneNumber, String contactTypeName,String userEmail){
        return repository.findByParams(firstName, lastName, address, phoneNumber, contactTypeName, userEmail);
    }
}
