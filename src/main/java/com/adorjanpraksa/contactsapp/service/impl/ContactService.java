package com.adorjanpraksa.contactsapp.service.impl;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ContactService{

    private final ContactRepository repository;

    public List<Contact> findAllUsersContacts(String email) {

        return repository.findByUserProfileEmail(email);


    }
}
