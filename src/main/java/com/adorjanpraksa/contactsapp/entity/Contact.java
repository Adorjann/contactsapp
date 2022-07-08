package com.adorjanpraksa.contactsapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private ContactType contactType;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;


}
