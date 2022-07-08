package com.adorjanpraksa.contactsapp.web.mapper;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.entity.UserRole;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileCreationDto;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class UserProfileMapper {

    public UserProfile mapToEntity(UserProfile userProfile, UserProfileDto dto) {

        if (nonNull(dto.getEmail())) {
            userProfile.setEmail(dto.getEmail());
        }
        if (nonNull(dto.getFirstName())) {
            userProfile.setFirstName(dto.getFirstName());
        }
        if (nonNull(dto.getLastName())) {
            userProfile.setLastName(dto.getLastName());
        }

        return userProfile;
    }

    public UserProfile mapToEntity(UserProfileCreationDto dto) {

        UserProfile userProfile = new UserProfile();

        userProfile.setEmail(dto.getEmail());
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());
        userProfile.setRole(UserRole.USER);

        return userProfile;

    }

    public UserProfileDto mapToDto(UserProfile userProfile) {

        UserProfileDto dto = new UserProfileDto();

        dto.setId(userProfile.getId());
        dto.setEmail(userProfile.getEmail());
        dto.setFirstName(userProfile.getFirstName());
        dto.setLastName(userProfile.getLastName());

        return dto;
    }
}
