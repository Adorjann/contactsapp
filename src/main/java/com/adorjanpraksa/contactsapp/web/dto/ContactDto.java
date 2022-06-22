package com.adorjanpraksa.contactsapp.web.dto;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import com.adorjanpraksa.contactsapp.entity.UserProfile;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class ContactDto {

    private Long id;

    private String firstName;

    private String lastName;

    @Email
    private String address;
    @Pattern(regexp = "^\\d{6,12}$")
    private String phoneNumber;

    private Long contactTypeId;

    private Long userProfileId;

    public ContactDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(Long contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }
}
