package com.adorjanpraksa.contactsapp.service;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.service.dao.NotificationDao;
import com.adorjanpraksa.contactsapp.service.dao.UserProfileDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final UserProfileDao userProfileDao;
    private final PasswordEncoder passwordEncoder;
    private final NotificationDao notificationDao;


    public UserProfile findById(Long userProfileId) {

        return userProfileDao.findById(userProfileId);
    }

    @Transactional
    public UserProfile saveNew(UserProfile userProfile, String clearTextPassword) {

        userProfile.setPassword(passwordEncoder.encode(clearTextPassword));

        var savedUserProfile = userProfileDao.save(userProfile);
        notificationDao.sendEmail(userProfile.getFirstName(), userProfile.getEmail());

        return savedUserProfile;
    }

    public void updateUserProfile(String oldEmail, UserProfile editedUserProfile) {

        userProfileDao.update(oldEmail, editedUserProfile);
    }
}
