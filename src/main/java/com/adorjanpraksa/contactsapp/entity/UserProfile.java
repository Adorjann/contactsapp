package com.adorjanpraksa.contactsapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String email;
    private String firstName;
    private String lastName;
    private String password;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private Set<Contact> contacts = new HashSet<Contact>();
}
