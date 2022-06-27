package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.service.impl.ContactService;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import com.adorjanpraksa.contactsapp.web.mapper.ContactMapper;
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

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDto>> getContacts(@AuthenticationPrincipal UserDetails userDetails) {

        var Contacts = contactService.findAllUsersContacts(userDetails.getUsername());

        return new ResponseEntity<>(contactMapper.mapToDto(Contacts), HttpStatus.OK);
    }


    @PostMapping("/create-contact")
    public ResponseEntity<ContactDto> createNew(@RequestBody @Valid ContactDto dto,
                                                @AuthenticationPrincipal UserDetails userDetails) {

        var contactToSave = contactMapper.mapToEntity(dto,userDetails.getUsername());
        if (contactToSave.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var savedContact = contactService.save(contactToSave.get());

        return new ResponseEntity<>(contactMapper.mapToDto(savedContact), HttpStatus.CREATED);
    }

    @PutMapping("/edit-contact/{id}")
    public ResponseEntity<ContactDto> editContact(@PathVariable Long id,
                                                  @RequestBody @Valid ContactDto dto,
                                                  @AuthenticationPrincipal UserDetails userDetails) {

        var contactToUpdate = contactMapper.mapToEntity(dto, userDetails.getUsername(),id);
        if (contactToUpdate.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Contact updatedContact = contactService.update(contactToUpdate.get());

        return new ResponseEntity<>(contactMapper.mapToDto(updatedContact), HttpStatus.OK);
    }

    @DeleteMapping("/delete-contact/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetails userDetails) {

        var deletedContact = contactService.delete(id, userDetails.getUsername());
        if (deletedContact.isPresent()){

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
