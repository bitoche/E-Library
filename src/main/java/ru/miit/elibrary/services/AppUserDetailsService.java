package ru.miit.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.miit.elibrary.models.AppUserDetails;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.repository.IUserRepository;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository appUserRepos;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = appUserRepos.findAppUserByEmail(email);
        return user.map(AppUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(email + " не найден"));
    }
}
