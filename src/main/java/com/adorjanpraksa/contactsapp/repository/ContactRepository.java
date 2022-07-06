package com.adorjanpraksa.contactsapp.repository;

import com.adorjanpraksa.contactsapp.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {


    List<Contact> findByUserProfileId(Long userId);

    Page<Contact> findByUserProfileId(Long userId, Pageable pageable);
}
