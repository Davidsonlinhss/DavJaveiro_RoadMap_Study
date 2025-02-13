package com.davjaveiro.helloworldjpa.springsecuritytest.auth;

import com.davjaveiro.helloworldjpa.springsecuritytest.model.User;
import com.davjaveiro.helloworldjpa.springsecuritytest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.map(CustomerUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Username not found: " + email));
    }
}
