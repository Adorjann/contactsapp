package com.adorjanpraksa.contactsapp.service.dao;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.repository.ContactTypeRepository;
import com.adorjanpraksa.contactsapp.service.exception.DuplicateDataException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactTypesDao {

    private final ContactTypeRepository contactTypeRepository;

    public ContactType findByTypeName(String contactTypeName) {

        return contactTypeRepository.findByType(contactTypeName)
                .orElseThrow(() -> new NotFoundException("Contact Type " + contactTypeName + " is not found."));

    }

    public ContactType save(ContactType contactType) {

        if (contactTypeRepository.existsContactTypeByType(contactType.getType())) {
            throw new DuplicateDataException("Contact type " + contactType.getType() + " already exists.");
        }

        return contactTypeRepository.save(contactType);
    }

    public ContactType findById(Long contactTypeId) {

        return contactTypeRepository.findById(contactTypeId)
                .orElseThrow(() -> new NotFoundException("Contact type with id " + contactTypeId + " is not found"));

    }


}
