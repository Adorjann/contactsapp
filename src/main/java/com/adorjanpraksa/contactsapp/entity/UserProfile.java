package com.adorjanpraksa.contactsapp.entity;

import com.adorjanpraksa.contactsapp.enumeration.UserRole;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserProfile {
    @Id
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
