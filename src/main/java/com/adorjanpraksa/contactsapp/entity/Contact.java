package com.adorjanpraksa.contactsapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    @ManyToOne
    private ContactType contactType;

    @ManyToOne
    private UserProfile userProfile;


}
