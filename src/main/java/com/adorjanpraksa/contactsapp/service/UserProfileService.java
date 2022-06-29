package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.repository.UserProfileRepository;
import com.adorjanpraksa.contactsapp.service.exception.DuplicateDataException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;


    public UserProfile findById(Long userProfileId) {

        var optionalUserProfile = userProfileRepository.findById(userProfileId);
        if (optionalUserProfile.isEmpty()) {
            throw new NotFoundException("User profile with id " + userProfileId + " is not found");
        }
        return optionalUserProfile.get();
    }


    public UserProfile saveNew(UserProfile userProfile, String clearTextPassword) {

        if (userProfileRepository.existsByEmail(userProfile.getEmail())) {
            throw new DuplicateDataException("User with email " + userProfile.getEmail() + " already exists");
        }
        userProfile.setPassword(passwordEncoder.encode(clearTextPassword));

        return userProfileRepository.save(userProfile);
    }

    public void updateUserProfile(UserProfile editedUser, Long userId) {

        var userFromDb = findById(userId);

        userFromDb.setEmail(editedUser.getEmail());
        userFromDb.setFirstName(editedUser.getFirstName());
        userFromDb.setLastName(editedUser.getLastName());

        userProfileRepository.save(userFromDb);
    }
}
