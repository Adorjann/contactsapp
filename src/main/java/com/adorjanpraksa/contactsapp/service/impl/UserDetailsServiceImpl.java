package com.adorjanpraksa.contactsapp.service.impl;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@RequiredArgsConstructor
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserProfileService userProfileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserProfile> userProfile = userProfileService.findByEmail(username);

        if (userProfile.isEmpty()){
            throw new UsernameNotFoundException(String.format("No user found with eMail '%s'.", username));
        }
        else{
             Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            String role = String.format("ROLE_%s", userProfile.get().getRole().toString()) ;

            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(
                    userProfile.get().getEmail(),
                    userProfile.get().getPassword(),
                    grantedAuthorities);

        }
    }
}
