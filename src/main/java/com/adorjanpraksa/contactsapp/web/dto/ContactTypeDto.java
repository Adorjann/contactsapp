package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContactTypeDto {

    @NotBlank
    private String type;
}
