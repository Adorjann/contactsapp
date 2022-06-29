package com.adorjanpraksa.contactsapp.security;

import com.adorjanpraksa.contactsapp.entity.UserProfile;
import com.adorjanpraksa.contactsapp.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private final UserProfileRepository userProfileRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserProfile> userProfile = userProfileRepository.findByEmail(username);

        if (userProfile.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No user found with eMail '%s'.", username));
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            String role = userProfile.get().getRole().toString();

            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return CustomUserDetails.builder()
                    .id(userProfile.get().getId())
                    .password(userProfile.get().getPassword())
                    .email(userProfile.get().getEmail())
                    .grantedAuthorities(grantedAuthorities)
                    .build();

        }
    }
}
