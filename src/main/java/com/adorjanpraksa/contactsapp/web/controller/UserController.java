package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.security.CustomUserDetails;
import com.adorjanpraksa.contactsapp.service.ContactService;
import com.adorjanpraksa.contactsapp.web.dto.ContactDto;
import com.adorjanpraksa.contactsapp.web.dto.PageDto;
import com.adorjanpraksa.contactsapp.web.mapper.ContactMapper;
import com.adorjanpraksa.contactsapp.web.mapper.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    private final PageMapper pageMapper;


    @GetMapping("/contacts")
    public ResponseEntity<PageDto> getContacts(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @PageableDefault Pageable pageable) {


        Page<Contact> contacts = contactService.findAllUsersContactsPaginated(userDetails.getId(), pageable);

        var pageDto = pageMapper.mapToDto(contacts.map(contactMapper::mapToDto));

        return ResponseEntity.ok(pageDto);

    }

    @GetMapping("/contact/{contactId}")
    public ResponseEntity<ContactDto> getOneContact(@PathVariable Long contactId,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails) {

        var contact = contactService.findOneUsersContact(contactId, userDetails.getId());

        return ResponseEntity.ok(contactMapper.mapToDto(contact));
    }

    @PostMapping("/contact")
    public ResponseEntity<ContactDto> createNewContact(@RequestBody @Validated(ContactDto.CreateContact.class) ContactDto contactDto,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails,
                                                       HttpServletRequest request) {

        var contactToSave = contactMapper.mapToEntity(contactDto);

        var savedContact = contactService.save(contactToSave, userDetails.getId(), contactDto.getContactTypeName());

        return ResponseEntity.created(getLocationHeader(request, "/user/contact/", savedContact.getId())).build();
    }

    private URI getLocationHeader(HttpServletRequest request, String urlPath, Long resourceId) {

        return URI.create("http://".concat(request.getServerName().concat(":") + request.getServerPort())
                .concat(urlPath).concat(resourceId.toString()));
    }

    @PutMapping("/contact/{contactId}")
    public ResponseEntity<ContactDto> editContact(@PathVariable Long contactId,
                                                  @RequestBody @Validated(ContactDto.UpdateContact.class) ContactDto contactDto,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails) {

        var contactFromDb = contactService.findOneUsersContact(contactId, userDetails.getId());
        var contactToUpdate = contactMapper.mapToEntity(contactFromDb, contactDto);

        contactService.update(contactToUpdate, contactDto.getContactTypeName());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/contact/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long contactId,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {

        contactService.delete(contactId, userDetails.getId());

        return ResponseEntity.noContent().build();
    }


}
