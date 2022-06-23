package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.service.ContactTypeService;
import com.adorjanpraksa.contactsapp.service.impl.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final ContactTypeService contactTypeService;
    private final ContactService     contactService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactType> createNew(@RequestBody ContactType contactType) {

        ContactType savedType = contactTypeService.saveNew(contactType);

        return new ResponseEntity<>(contactType, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactType> edit(@PathVariable Long id,
                                            @RequestBody ContactType contactType) {

        ContactType updatedType = contactTypeService.update(contactType);

        return new ResponseEntity<>(updatedType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        var deletedContact = contactService.delete(id);
        if (deletedContact.isPresent()){

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
