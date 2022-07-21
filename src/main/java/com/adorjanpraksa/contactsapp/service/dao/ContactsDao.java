package com.adorjanpraksa.contactsapp.service.dao;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.repository.ContactRepository;
import com.adorjanpraksa.contactsapp.service.exception.ForbiddenAccessException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class ContactsDao {

    private final ContactRepository contactRepository;

    public Contact getContactById(Long contactId) {

        return contactRepository.findById(contactId)
                .orElseThrow(() -> new NotFoundException("Contact with id " + contactId + " is not found."));

    }

    public Contact save(Contact contact) {

        return contactRepository.save(contact);
    }

    public void delete(Long id) {

        contactRepository.deleteById(id);
    }

    public void delete(Contact contact) {

        contactRepository.delete(contact);
    }

    public Contact getOneUsersContact(Long contactId, Long userId) {

        var contact = getContactById(contactId);

        if (!userId.equals(contact.getUserProfile().getId())) {
            throw new ForbiddenAccessException("User with id " + userId + " is not allowed to access another user's contact");
        }

        return contact;
    }

    public Page<Contact> getAllContacts(Pageable pageable, LocalDateTime from, LocalDateTime to) {

        return contactRepository.findAllByCreatedAtBetween(pageable, from, to);
    }

    public Page<Contact> findAllUsersContacts(Long userId, Pageable pageable) {

        return contactRepository.findByUserProfileId(userId, pageable);
    }

}
