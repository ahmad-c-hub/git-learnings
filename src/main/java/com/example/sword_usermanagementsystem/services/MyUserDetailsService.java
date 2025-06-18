package com.example.sword_usermanagementsystem.services;

import com.example.sword_usermanagementsystem.models.UserM;
import com.example.sword_usermanagementsystem.models.UserPrinciple;
import com.example.sword_usermanagementsystem.repos.UserRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserM> user = userRepo.findByUserName(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        UserM userM = user.get();
        return new UserPrinciple(userM);
    }
}
