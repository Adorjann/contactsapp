package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserProfileCreationDto extends UserProfileDto {


    @NotNull
    @NotBlank
    private String password;
}
