package com.yehor.phonecontacts.service;

import com.yehor.phonecontacts.api.model.User;
import com.yehor.phonecontacts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User foundUser = userRepository.findByLogin(currentUser.getLogin()).orElse(null);

        if (foundUser == null) {
            throw new UsernameNotFoundException("Cannot find user with this login");
        }
        return foundUser;
    }
}
