package com.adorjanpraksa.contactsapp.service.impl;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository  repository;
    private final UserProfileService userProfileService;

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

    public Optional<Contact> delete(Long id, String userEmail) {

        var userOptional = userProfileService.findByEmail(userEmail);
        var contact = findById(id);

        //checking if user is deleting his own contact
        if (!Objects.equals(userOptional.get().getId(),contact.get().getUserProfile().getId())){
            return Optional.empty();
        }

        contact.ifPresent(repository::delete);
        return contact;
    }
    public Optional<Contact> delete(Long contactId) {

        var contact = findById(contactId);

        contact.ifPresent(repository::delete);
        return contact;
    }








}
