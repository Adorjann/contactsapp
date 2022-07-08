package com.adorjanpraksa.contactsapp.repository;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByEmail(String email);

    boolean existsByEmail(String email);
}
