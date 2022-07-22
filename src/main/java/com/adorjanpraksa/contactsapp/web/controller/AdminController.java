package com.adorjanpraksa.contactsapp.web.controller;

import com.adorjanpraksa.contactsapp.entity.Contact;
import com.adorjanpraksa.contactsapp.service.ContactService;
import com.adorjanpraksa.contactsapp.service.ContactTypeService;
import com.adorjanpraksa.contactsapp.service.NasaService;
import com.adorjanpraksa.contactsapp.service.UserProfileService;
import com.adorjanpraksa.contactsapp.web.dto.*;
import com.adorjanpraksa.contactsapp.web.mapper.ContactMapper;
import com.adorjanpraksa.contactsapp.web.mapper.ContactTypeMapper;
import com.adorjanpraksa.contactsapp.web.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final ContactTypeService contactTypeService;
    private final ContactService contactService;
    private final UserProfileService userProfileService;
    private final UserProfileMapper userProfileMapper;
    private final ContactTypeMapper contactTypeMapper;
    private final ContactMapper contactMapper;
    private final NasaService nasaService;

    @GetMapping("/rover-images")
    public ResponseEntity<Map<String, List<String>>> getMarsImages(@RequestParam(name = "rover") String rover,
                                                                   @RequestParam(name = "camera") String camera){

        return ResponseEntity.ok(nasaService.getImages(rover,camera));
    }

    @PostMapping("/contacts")
    public ResponseEntity<PageDto> getAllContacts(@PageableDefault Pageable pageable,
                                                  @RequestBody @Valid AllContactsRequestDto allContactsRequestDto) {

        Page<Contact> page = contactService.getAllContacts(pageable, allContactsRequestDto.getFrom(), allContactsRequestDto.getTo());

        return ResponseEntity.ok(PageDto.builder()
                .content(page.map(contactMapper::mapToDto).getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .build());
    }

    @PostMapping("/contact-type")
    public ResponseEntity<ContactTypeDto> createNewContactType(@RequestBody @Valid ContactTypeDto contactTypeDto,
                                                               HttpServletRequest request) {

        var contactTypeToSave = contactTypeMapper.mapToEntity(contactTypeDto);

        var savedType = contactTypeService.saveNew(contactTypeToSave);

        return ResponseEntity.created(getLocationHeader(request, "/admin/contact-type/", savedType.getId())).build();
    }

    private URI getLocationHeader(HttpServletRequest request, String urlPath, Long resourceId) {

        return URI.create("http://".concat(request.getServerName().concat(":") + request.getServerPort())
                .concat(urlPath).concat(resourceId.toString()));
    }

    @GetMapping("/contact-type/{contactTypeId}")
    public ResponseEntity<ContactTypeDto> getOneContactType(@PathVariable Long contactTypeId) {

        var contactType = contactTypeService.findById(contactTypeId);

        return ResponseEntity.ok(contactTypeMapper.mapToDto(contactType));
    }

    @PutMapping("/contact-type/{contactTypeId}")
    public ResponseEntity<ContactTypeDto> editContactType(@PathVariable Long contactTypeId,
                                                          @RequestBody @Valid ContactTypeDto dto) {

        var entityToUpdate = contactTypeMapper.mapToEntity(dto);

        contactTypeService.update(entityToUpdate, contactTypeId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/contact/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long contactId) {

        contactService.delete(contactId);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/user")
    public ResponseEntity<UserProfileDto> createNewUserProfile(@RequestBody @Validated(UserProfileDto.CreateUserProfile.class) UserProfileCreationDto userProfileCreationDto,
                                                               HttpServletRequest request) {

        var userToSave = userProfileMapper.mapToEntity(userProfileCreationDto);

        var savedUser = userProfileService.saveNew(userToSave, userProfileCreationDto.getPassword());

        return ResponseEntity.created(getLocationHeader(request, "/admin/user/", savedUser.getId())).build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileDto> getOneUser(@PathVariable Long userId) {

        var userProfile = userProfileService.findById(userId);

        return ResponseEntity.ok(userProfileMapper.mapToDto(userProfile));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserProfileDto> editUserProfile(@PathVariable Long userId,
                                                          @RequestBody @Validated(UserProfileDto.UpdateUserProfile.class) UserProfileDto userProfileDto) {

        var userFromDb = userProfileService.findById(userId);

        var oldEmail = userFromDb.getEmail();

        var userToUpdate = userProfileMapper.mapToEntity(userFromDb, userProfileDto);

        userProfileService.updateUserProfile(oldEmail, userToUpdate);

        return ResponseEntity.noContent().build();
    }


}
