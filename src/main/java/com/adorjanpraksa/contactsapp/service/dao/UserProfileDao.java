package com.adorjanpraksa.contactsapp.service.dao;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.repository.UserProfileRepository;
import com.adorjanpraksa.contactsapp.service.exception.DuplicateDataException;
import com.adorjanpraksa.contactsapp.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserProfileDao {

    private final UserProfileRepository userProfileRepository;

    public UserProfile findById(Long userId) {

        return userProfileRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User profile with id " + userId + " is not found"));
    }

    public UserProfile save(UserProfile userProfile) {

        if (userProfileRepository.existsByEmail(userProfile.getEmail())) {
            throw new DuplicateDataException("User with email " + userProfile.getEmail() + " already exists");
        }

        return userProfileRepository.save(userProfile);
    }

    public void update(UserProfile userProfile) {

        var userFromDb = userProfileRepository.findByEmail(userProfile.getEmail());
        if (userFromDb.isPresent()) {

            if (!userFromDb.get().getId().equals(userProfile.getId())) {
                //email is changed into an existing mail
                throw new DuplicateDataException("User with email " + userProfile.getEmail() + " already exists");
            }
        }
        //email is changed into non-existing mail
        //email is not changed
        userProfileRepository.save(userProfile);
    }

}
