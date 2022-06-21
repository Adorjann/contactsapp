package com.adorjanpraksa.contactsapp.entity;

import com.adorjanpraksa.contactsapp.enumeration.UserRole;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user_profile")
@Data
public class UserProfile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "userProfile")
    private Set<Contact> contacts = new HashSet<Contact>();

    public void addContact(Contact contact)
    {
        if (contact.getUserProfile() != this)
        {
            contact.setUserProfile(this);
        }
        this.contacts.add(contact);
    }
}
