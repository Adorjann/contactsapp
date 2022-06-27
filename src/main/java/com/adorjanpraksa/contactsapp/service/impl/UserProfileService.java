package com.adorjanpraksa.contactsapp.service.impl;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.enumeration.UserRole;
import com.adorjanpraksa.contactsapp.repository.UserProfileRepository;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileCreationDto;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    public UserProfile saveNew(UserProfile userProfile) {

        userProfile.setRole(UserRole.USER);
        return repository.save(userProfile);
    }

    public Optional<UserProfile> updateUserProfile(UserProfile editedUser) {

        var userProfileFromDbOptional = findByEmail(editedUser.getEmail());

        if (userProfileFromDbOptional.isEmpty()){
            return Optional.empty();
        }
        var userFromDb = userProfileFromDbOptional.get();
        userFromDb.setEmail(editedUser.getEmail());
        userFromDb.setFirstName(editedUser.getFirstName());
        userFromDb.setLastName(editedUser.getLastName());

        return Optional.of(saveNew(userFromDb));
    }
}
