package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.service.dao.ContactTypesDao;
import com.adorjanpraksa.contactsapp.service.dao.ContactsDao;
import com.adorjanpraksa.contactsapp.service.dao.UserProfileDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactTypesDao contactTypesDao;
    private final UserProfileDao userProfileDao;
    private final ContactsDao contactsDao;


    public List<Contact> findAllUsersContacts(Long userId) {

        return contactsDao.getAllUsersContacts(userId);
    }

    public Contact findOneUsersContact(Long contactId, Long userId) {

        return contactsDao.getOneUsersContact(contactId, userId);
    }

    public Contact save(Contact contact, Long userId, String type) {

        var contactType = contactTypesDao.findByTypeName(type);
        var userProfile = userProfileDao.findById(userId);

        contact.setContactType(contactType);
        contact.setUserProfile(userProfile);

        return contactsDao.save(contact);
    }

    public void update(Contact editedContact, Long contactId, Long userId, String contactTypeName) {

        var contactToUpdate = contactsDao.getOneUsersContact(contactId, userId);

        var contactType = contactTypesDao.findByTypeName(contactTypeName);

        contactToUpdate.setFirstName(editedContact.getFirstName());
        contactToUpdate.setLastName(editedContact.getLastName());
        contactToUpdate.setAddress(editedContact.getAddress());
        contactToUpdate.setPhoneNumber(editedContact.getPhoneNumber());
        contactToUpdate.setContactType(contactType);

        contactsDao.save(contactToUpdate);
    }

    public void delete(Long contactId, Long userId) {

        var contact = contactsDao.getOneUsersContact(contactId, userId);

        contactsDao.delete(contact);
    }

    public void delete(Long contactId) {

        contactsDao.delete(contactId);
    }


}
