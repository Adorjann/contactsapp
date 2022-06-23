package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.repository.ContactTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactTypeService {

    private final ContactTypeRepository repository;

    public ContactType saveNew(ContactType contactType) {
        return repository.save(contactType);
    }

    public ContactType update(ContactType contactType) {
        return repository.save(contactType);
    }

    public Optional<ContactType> findByTypeName(String contactTypeName) {

        return repository.findByType(contactTypeName);
    }
}
