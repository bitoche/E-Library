package ru.miit.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
public class ElibAuthenticationProvider implements AuthenticationProvider {
    private final AppUserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ElibAuthenticationProvider(AppUserDetailsService userDetailsService,
                                      UserService userService,
                                      PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        String entryCode = (String) authentication.getDetails();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            if (isValidEntryCode(email, password, entryCode)) {
                return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException("Invalid entry code");
            }
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    private boolean isValidEntryCode(String email, String password, String entryCode) {
        return userService.loginWithOneTimeCode(email, password, entryCode);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}