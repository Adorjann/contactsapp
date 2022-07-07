package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserProfileCreationDto extends UserProfileDto {


    @NotBlank(groups = CreateUserProfile.class)
    private String password;
}
