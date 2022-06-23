package com.adorjanpraksa.contactsapp.repository;

import com.adorjanpraksa.contactsapp.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType,Long> {
    Optional<ContactType> findByType(String contactTypeName);
}
