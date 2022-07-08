package com.adorjanpraksa.contactsapp.web.mapper;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.web.dto.ContactTypeDto;
import org.springframework.stereotype.Component;

@Component
public class ContactTypeMapper {

    public ContactType mapToEntity(ContactTypeDto dto) {

        var entity = new ContactType();

        entity.setType(dto.getType());

        return entity;
    }

    public ContactTypeDto mapToDto(ContactType contactType) {

        var dto = new ContactTypeDto();

        dto.setType(contactType.getType());
        dto.setId(contactType.getId());

        return dto;
    }
}
