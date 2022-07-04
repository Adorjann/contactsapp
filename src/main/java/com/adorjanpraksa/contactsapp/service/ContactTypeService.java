package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.service.dao.ContactTypesDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactTypeService {

    private final ContactTypesDao contactTypesDao;

    public ContactType saveNew(ContactType contactType) {

        return contactTypesDao.save(contactType);
    }

    public ContactType findById(Long contactTypeId) {

        return contactTypesDao.findById(contactTypeId);
    }

    public void update(ContactType editedContactType, Long contactTypeId) {

        var contactTypeFromDb = contactTypesDao.findById(contactTypeId);

        contactTypeFromDb.setType(editedContactType.getType());

        contactTypesDao.save(contactTypeFromDb);
    }


}
