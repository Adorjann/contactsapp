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

        userProfileDao.save(userProfile);
        notificationDao.sendEmail(userProfile.getFirstName(), userProfile.getEmail());

        return userProfile;
    }


    public void updateUserProfile(UserProfile editedUser, Long userId) {

        var userFromDb = userProfileDao.findById(userId);

        userFromDb.setEmail(editedUser.getEmail());
        userFromDb.setFirstName(editedUser.getFirstName());
        userFromDb.setLastName(editedUser.getLastName());

        userProfileDao.update(userFromDb);
    }
}
