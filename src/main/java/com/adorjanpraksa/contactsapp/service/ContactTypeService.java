package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.repository.ContactTypeRepository;
import com.adorjanpraksa.contactsapp.web.dto.ContactTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactTypeService {

    private final ContactTypeRepository repository;

    public ContactType saveNew(ContactType contactType) {
        //contactType.setId(33L);
        return repository.save(contactType);
    }

    public Optional<ContactType> findOneById(Long id){
        return repository.findById(id);
    }

    public Optional<ContactType> update(ContactType editedContactType,Long id) {

        var contactTypeFromDbOptional = findOneById(id);

        if (contactTypeFromDbOptional.isEmpty()){
            return Optional.empty();
        }

        editedContactType.setId(contactTypeFromDbOptional.get().getId());
        return Optional.of(repository.save(editedContactType));
    }

    public Optional<ContactType> findByTypeName(String contactTypeName) {

        return repository.findByType(contactTypeName);
    }

}
