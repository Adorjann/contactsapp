package com.adorjanpraksa.contactsapp.configuration;

import com.adorjanpraksa.contactsapp.entity.UserRole;
import com.adorjanpraksa.contactsapp.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/admin/**").hasAuthority(UserRole.ADMIN.name())
                        .antMatchers("/user/**").hasAuthority(UserRole.USER.name())
                        .antMatchers("/**").denyAll()
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and().build();
    }

    @Bean
    public DaoAuthenticationProvider userDetailsService() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(this.userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


}
