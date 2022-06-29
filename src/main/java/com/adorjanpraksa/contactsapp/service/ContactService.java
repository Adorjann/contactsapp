package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.repository.ContactRepository;
import com.adorjanpraksa.contactsapp.service.exception.ForbiddenAccessException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactTypeService contactTypeService;
    private final UserProfileService userProfileService;

    public Contact findById(Long contactId) {

        var contact = contactRepository.findById(contactId);

        if (contact.isEmpty()) {
            throw new NotFoundException("Contact with id " + contactId + " is not found.");
        }

        return contact.get();
    }

    public List<Contact> findAllUsersContacts(Long userId) {

        return contactRepository.findByUserProfileId(userId);
    }

    public Contact findOneUsersContact(Long contactId, Long userId) {

        var contact = findById(contactId);

        if (!userId.equals(contact.getUserProfile().getId())) {
            throw new ForbiddenAccessException("User with id " + userId + " is not allowed to access another user's contact");
        }

        return contact;
    }

    public Contact save(Contact contact, Long userId, String type) {

        var contactType = contactTypeService.findByTypeName(type);
        var userProfile = userProfileService.findById(userId);

        contact.setContactType(contactType);
        contact.setUserProfile(userProfile);

        return contactRepository.save(contact);
    }

    public void update(Contact contact, Long contactId, Long userId, String contactTypeName) {

        var contactToUpdate = findById(contactId);
        var userProfile = userProfileService.findById(userId);
        var contactType = contactTypeService.findByTypeName(contactTypeName);

        if (!userProfile.getId().equals(contactToUpdate.getUserProfile().getId())) {
            throw new ForbiddenAccessException("User " + userProfile.getFirstName() + " is unallowed to alter another user's contact");
        }
        contact.setContactType(contactType);
        contact.setUserProfile(userProfile);
        contact.setId(contactId);

        contactRepository.save(contact);
    }

    public void delete(Long id, Long userId) {

        var userProfile = userProfileService.findById(userId);
        var contact = findById(id);

        //checking if user is deleting his own contact
        if (!userProfile.getId().equals(contact.getUserProfile().getId())) {
            throw new ForbiddenAccessException("User " + userProfile.getFirstName() + " is not allowed to alter another user's contact");
        }

        contactRepository.delete(contact);
    }

    public void delete(Long contactId) {

        var contact = findById(contactId);

        contactRepository.delete(contact);
    }


}
