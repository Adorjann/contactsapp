package com.adorjanpraksa.contactsapp.web.mapper;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.service.ContactTypeService;
import com.adorjanpraksa.contactsapp.web.dto.ContactTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ContactTypeMapper {

    private final ContactTypeService contactTypeService;

    public ContactType mapToEntity (ContactTypeDto dto){

        var contactOptional = contactTypeService.findByTypeName(dto.getType());
        var entity = contactOptional.orElseGet(ContactType::new);

        entity.setType(dto.getType());

        return  entity;
    }

    public ContactTypeDto mapToDto(ContactType contactType) {

        var dto = new ContactTypeDto();
        dto.setType(contactType.getType());

        return dto;
    }
}
