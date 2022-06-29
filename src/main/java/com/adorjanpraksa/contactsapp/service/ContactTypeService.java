package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.repository.ContactTypeRepository;
import com.adorjanpraksa.contactsapp.service.exception.DuplicateDataException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactTypeService {

    private final ContactTypeRepository repository;

    public ContactType saveNew(ContactType contactType) {

        if (repository.existsContactTypeByType(contactType.getType())) {
            throw new DuplicateDataException("Contact type " + contactType.getType() + " already exists.");
        }

        return repository.save(contactType);
    }

    public ContactType findById(Long contactTypeId) {

        var contactType = repository.findById(contactTypeId);

        if (contactType.isEmpty()) {
            throw new NotFoundException("Contact type with id " + contactTypeId + " is not found");
        }

        return contactType.get();
    }

    public void update(ContactType editedContactType, Long contactTypeId) {

        var contactTypeFromDb = findById(contactTypeId);

        editedContactType.setId(contactTypeFromDb.getId());

        repository.save(editedContactType);
    }

    public ContactType findByTypeName(String contactTypeName) {

        var contactType = repository.findByType(contactTypeName);

        if (contactType.isEmpty()) {
            throw new NotFoundException("Contact Type " + contactTypeName + " is not found.");
        }

        return contactType.get();
    }

}
