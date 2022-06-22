package com.adorjanpraksa.contactsapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ContactType {
    @Id
    private Long id;

    private String type;


}
