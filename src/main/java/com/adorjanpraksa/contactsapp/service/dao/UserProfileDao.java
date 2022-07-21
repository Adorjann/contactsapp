package com.adorjanpraksa.contactsapp.service.dao;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.repository.UserProfileRepository;
import com.adorjanpraksa.contactsapp.service.exception.DuplicateDataException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserProfileDao {

    private final UserProfileRepository userProfileRepository;

    public UserProfile findById(Long userId) {

        return userProfileRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User profile with id " + userId + " is not found"));
    }

    public UserProfile findByEmail(String email) {

        return userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with eMail '%s'.", email)));
    }

    public UserProfile save(UserProfile userProfile) {

        if (userProfileRepository.existsByEmail(userProfile.getEmail())) {
            throw new DuplicateDataException("User with email " + userProfile.getEmail() + " already exists");
        }

        return userProfileRepository.save(userProfile);
    }

    public void update(String oldEmail, UserProfile editedUserProfile) {

        if (!oldEmail.equals(editedUserProfile.getEmail())) {

            if (userProfileRepository.existsByEmail(editedUserProfile.getEmail())) {
                throw new DuplicateDataException("User with email " + editedUserProfile.getEmail() + " already exists");
            }
        }

        userProfileRepository.save(editedUserProfile);
    }
}
