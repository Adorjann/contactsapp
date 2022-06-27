package com.adorjanpraksa.contactsapp.web.mapper;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.service.impl.UserProfileService;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileCreationDto;
import com.adorjanpraksa.contactsapp.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class UserProfileMapper {


    public UserProfile mapToEntity(UserProfileDto dto){

        UserProfile userProfile = new UserProfile();

        userProfile.setEmail(dto.getEmail());
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());

        return userProfile;

    }
    public UserProfile mapToEntity(UserProfileCreationDto dto){

        UserProfile userProfile = new UserProfile();

        userProfile.setEmail(dto.getEmail());
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());
        userProfile.setPassword(dto.getPassword());

        return userProfile;

    }

    public UserProfileDto mapToDto(UserProfile userProfile) {

        UserProfileDto dto = new UserProfileDto();

        dto.setEmail(userProfile.getEmail());
        dto.setFirstName(userProfile.getFirstName());
        dto.setLastName(userProfile.getLastName());

        return dto;
    }
}
