package com.adorjanpraksa.contactsapp.web.mapper;

import com.adorjanpraksa.contactsapp.web.dto.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {

    public PageDto mapToDto(Page page) {

        PageDto dto = new PageDto();

        dto.setContent(page.getContent());
        dto.setPageNumber(page.getNumber());
        dto.setSize(page.getSize());
        dto.setTotalElements(page.getTotalElements());

        return dto;
    }
}
