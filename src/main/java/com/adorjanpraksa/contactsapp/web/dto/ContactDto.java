package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class ContactDto {

    private Long id;

    @NotBlank(groups = CreateContact.class)
    @Pattern(regexp = "^(?!\\s*$).+",
            message = "Must not be blank",
            groups = UpdateContact.class)
    private String firstName;

    @NotBlank(groups = CreateContact.class)
    @Pattern(regexp = "^(?!\\s*$).+",
            message = "Must not be blank",
            groups = UpdateContact.class)
    private String lastName;

    @NotBlank(groups = CreateContact.class)
    @Pattern(regexp = "^(?!\\s*$).+",
            message = "Must not be blank",
            groups = UpdateContact.class)
    private String address;

    @Pattern(regexp = "^\\d{6,12}$",
            message = "Phone number must contain 6 to 12 digits",
            groups = {CreateContact.class, UpdateContact.class})
    @NotBlank(groups = CreateContact.class)
    private String phoneNumber;

    @NotBlank(groups = CreateContact.class)
    @Pattern(regexp = "^(?!\\s*$).+",
            message = "Must not be blank",
            groups = UpdateContact.class)
    private String contactTypeName;


    public interface UpdateContact {
    }


    public interface CreateContact {
    }


}
