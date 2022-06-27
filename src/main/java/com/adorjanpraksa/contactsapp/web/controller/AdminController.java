package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.service.ContactTypeService;
import com.adorjanpraksa.contactsapp.service.impl.ContactService;
import com.adorjanpraksa.contactsapp.service.impl.UserProfileService;
import com.adorjanpraksa.contactsapp.web.dto.ContactTypeDto;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileCreationDto;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileDto;
import com.adorjanpraksa.contactsapp.web.mapper.ContactTypeMapper;
import com.adorjanpraksa.contactsapp.web.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final ContactTypeService contactTypeService;
    private final ContactService     contactService;
    private final UserProfileService userProfileService;
    private final UserProfileMapper  userProfileMapper;
    private final ContactTypeMapper  contactTypeMapper;
    private final PasswordEncoder    passwordEncoder;

    @PostMapping("/create-contact-type")
    public ResponseEntity<ContactTypeDto> createNewContactType(@RequestBody @Valid ContactTypeDto dto) {

        var savedType = contactTypeService.saveNew(contactTypeMapper.mapToEntity(dto));

        return new ResponseEntity<>(contactTypeMapper.mapToDto(savedType), HttpStatus.CREATED);
    }

    @PutMapping("/edit-contact-type/{id}")
    public ResponseEntity<ContactTypeDto> editContactType(@PathVariable Long id,
                                                          @RequestBody @Valid ContactTypeDto dto) {
        var entityToUpdate = contactTypeMapper.mapToEntity(dto);

        var updatedTypeOptional = contactTypeService.update(entityToUpdate, id);
        if (updatedTypeOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contactTypeMapper.mapToDto(updatedTypeOptional.get()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-contact/{id}")                                  //todo test this
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {

        var deletedContact = contactService.delete(id);
        if (deletedContact.isPresent()){

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/create-user")
    public ResponseEntity<UserProfileDto> createNewUserProfile(@RequestBody @Valid UserProfileCreationDto dto) {

        var userToSave = userProfileMapper.mapToEntity(dto);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        UserProfile savedUser = userProfileService.saveNew(userToSave);


        return new ResponseEntity<>(userProfileMapper.mapToDto(savedUser), HttpStatus.CREATED);
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<UserProfileDto> editUserProfile(@PathVariable Long id, @RequestBody @Valid UserProfileDto dto) {

        var userToUpdate = userProfileMapper.mapToEntity(dto);

        var updatedUserOptional = userProfileService.updateUserProfile(userToUpdate);

        if (updatedUserOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userProfileMapper.mapToDto(updatedUserOptional.get()), HttpStatus.OK);
    }


}
