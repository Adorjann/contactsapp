package com.adorjanpraksa.contactsapp.service.impl;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public Optional<UserProfile> findByEmail(String email) {

        return repository.findFirstByEmail(email);
    }

    public Optional<UserProfile> findById(Long userProfileId) {
        return repository.findById(userProfileId);
    }
}
