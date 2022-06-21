package com.adorjanpraksa.contactsapp.entity;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "contact")
@Data
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    private ContactType contactType;

    @ManyToOne
    private UserProfile userProfile;

    public void setContactType(ContactType contactType)
    {
        contactType.getContacts().add(this);
        this.contactType = contactType;
    }

    public void setUserProfile(UserProfile userProfile)
    {
        userProfile.getContacts().add(this);
        this.userProfile = userProfile;
    }
}
