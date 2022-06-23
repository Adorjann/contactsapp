package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.service.impl.ContactService;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final ContactService      contactService;

    @GetMapping
    public ResponseEntity<List<ContactDto>> get(@AuthenticationPrincipal UserDetails userDetails) {

        var Contacts = contactService.findAllUsersContacts(userDetails.getUsername());

        return new ResponseEntity<>(contactService.toContactDto(Contacts), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactDto> createNew(@RequestBody @Valid ContactDto dto,
                                                @AuthenticationPrincipal UserDetails userDetails) {

        var contactToSave =  contactService.toContact(dto);
        if (contactToSave.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var savedContact = contactService.save(contactToSave.get());

        return new ResponseEntity<>(contactService.toContactDto(savedContact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> edit(@PathVariable Long id,
                                           @RequestBody @Valid ContactDto dto,
                                           @AuthenticationPrincipal UserDetails userDetails) {

        var contactToUpdate =  contactService.toContact(dto);
        if (contactToUpdate.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Contact updatedContact = contactService.update(contactToUpdate.get());

        return new ResponseEntity<>(contactService.toContactDto(updatedContact), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal UserDetails userDetails) {

            var deletedContact = contactService.delete(id);
            if (deletedContact.isPresent()){

                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
