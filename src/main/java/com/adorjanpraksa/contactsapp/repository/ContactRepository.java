package com.adorjanpraksa.contactsapp.repository;

import com.adorjanpraksa.contactsapp.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    List<Contact> findByUserProfileEmail(String email);
}
