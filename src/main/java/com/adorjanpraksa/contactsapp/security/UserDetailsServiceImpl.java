package com.adorjanpraksa.contactsapp.security;

import com.adorjanpraksa.contactsapp.service.dao.UserProfileDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserProfileDao userProfileDao;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userProfile = userProfileDao.findByEmail(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>() {{
            add(new SimpleGrantedAuthority(userProfile.getRole().toString()));
        }};

        return CustomUserDetails.builder()
                .id(userProfile.getId())
                .password(userProfile.getPassword())
                .email(userProfile.getEmail())
                .grantedAuthorities(grantedAuthorities)
                .build();
    }
}
