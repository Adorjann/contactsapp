package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserProfileDto {

    private Long id;

    @NotBlank(groups = CreateUserProfile.class)
    @Email(groups = {CreateUserProfile.class, UpdateUserProfile.class})
    @Pattern(regexp = "^(?!\\s*$).+",
            message = "Must not be blank",
            groups = UpdateUserProfile.class)
    private String email;

    @NotBlank(groups = CreateUserProfile.class)
    @Pattern(regexp = "^(?!\\s*$).+",
            message = "Must not be blank",
            groups = UpdateUserProfile.class)
    private String firstName;

    @Pattern(regexp = "^(?!\\s*$).+",
            message = "Must not be blank",
            groups = UpdateUserProfile.class)
    @NotBlank(groups = CreateUserProfile.class)
    private String lastName;


    public interface UpdateUserProfile {
    }

    public interface CreateUserProfile {
    }
}
