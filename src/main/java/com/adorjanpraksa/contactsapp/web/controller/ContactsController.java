package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.service.impl.ContactService;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import com.adorjanpraksa.contactsapp.web.dtoConverter.ContactToContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactsController {

    private final ContactService contactService;
    private final ContactToContactDto toContactDto;

    @GetMapping
    public ResponseEntity<List<ContactDto>> get(@AuthenticationPrincipal UserDetails userDetails) {

        var Contacts = contactService.findAllUsersContacts(userDetails.getUsername());

        return new ResponseEntity<>(toContactDto.convert(Contacts), HttpStatus.OK);
    }


}
