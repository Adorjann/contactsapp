package com.adorjanpraksa.contactsapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "contact_type")
@Data
public class ContactType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "contactType")
    private Set<Contact> contacts = new HashSet<>();

    public void addContacts(Contact contact)
    {
        if (contact.getContactType() != this)
        {
            contact.setContactType(this);
        }
        this.contacts.add(contact);
    }
}
