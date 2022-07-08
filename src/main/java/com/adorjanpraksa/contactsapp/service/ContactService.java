package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.service.dao.ContactTypesDao;
import com.adorjanpraksa.contactsapp.service.dao.ContactsDao;
import com.adorjanpraksa.contactsapp.service.dao.UserProfileDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    public void update(Contact editedContact, String contactTypeName) {

        if (Objects.nonNull(contactTypeName)) {
            var contactType = contactTypesDao.findByTypeName(contactTypeName);
            editedContact.setContactType(contactType);
        }

        contactsDao.save(editedContact);
    }

    public void delete(Long contactId, Long userId) {

        var contact = contactsDao.getOneUsersContact(contactId, userId);

        contactsDao.delete(contact);
    }

    public void delete(Long contactId) {

        contactsDao.delete(contactId);
    }

    public Page<Contact> getAllContacts(Pageable pageable, LocalDateTime from, LocalDateTime to) {

        return contactsDao.getAllContacts(pageable, from, to);
    }

    public Page<Contact> findAllUsersContactsPaginated(Long userId, Pageable pageable) {

        return contactsDao.findAllUsersContacts(userId, pageable);
    }
}
