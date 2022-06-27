package com.adorjanpraksa.contactsapp.repository;

import com.adorjanpraksa.contactsapp.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    List<Contact> findByUserProfileEmail(String email);

    @Query("SELECT c FROM Contact c WHERE" +
            "(:firstName = NULL OR c.firstName = :firstName) AND " +
            "(:lastName = NULL OR c.lastName = :lastName) AND " +
            "(:address = NULL OR c.address = :address) AND " +
            "(:phoneNumber = NULL OR c.phoneNumber = :phoneNumber) AND " +
            "(:typeName = NULL OR c.contactType.type = :typeName) AND " +
            "(:email = NULL OR c.userProfile.email = :email)")
    List<Contact> findByParams(@Param("firstName") String firstName,
                               @Param("lastName") String lastName,
                               @Param("address") String address,
                               @Param("phoneNumber") String phoneNumber,
                               @Param("typeName") String type,
                               @Param("email") String email);
}
