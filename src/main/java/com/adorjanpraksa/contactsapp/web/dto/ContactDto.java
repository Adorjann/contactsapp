package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class ContactDto {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;
    @Pattern(regexp = "^\\d{6,12}$")
    private String phoneNumber;
    @NotBlank
    private String contactTypeName;


}
