package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContactTypeDto {

    private Long id;
    @NotBlank
    private String type;

}
