package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class ContactDto {


    private String firstName;

    private String lastName;

    private String address;
    @Pattern(regexp = "^\\d{6,12}$")
    private String phoneNumber;

    private String contactTypeName;  //ime typa


}
