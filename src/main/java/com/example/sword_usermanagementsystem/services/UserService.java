package com.example.sword_usermanagementsystem.services;

import com.example.sword_usermanagementsystem.models.UserM;
import com.example.sword_usermanagementsystem.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void add(UserM user) {
        if(user.getAge()<18) {
            throw new IllegalStateException("User should be at least 18");
        }else {
            Optional<UserM> userMOptional = userRepository.findByEmail(user.getEmail());
            Optional<UserM> userMNameOptional = userRepository.findByUserName(user.getUserName());
            if (userMOptional.isPresent() || userMNameOptional.isPresent()) {
                throw new IllegalStateException("User already exists");
            } else {
                userRepository.save(user);
            }
        }

    }
    public UserM register(UserM user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(UserM user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUserName());
        }
        return "fail";
    }

    public List<UserM> getUsers() {
        return userRepository.findAll();
    }

    public UserM getUserById(long id) {
        Optional<UserM> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        return userOptional.get();
    }

    public void updateUserById(Long id, UserM user) {
        Optional<UserM> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        UserM userToUpdate = userOptional.get();
        if(user.getUserName()!=null){
            userToUpdate.setName(user.getUserName());
        }
        if(user.getEmail()!=null){
            userToUpdate.setEmail(user.getEmail());
        }
        if(user.getJob_title()!=null){
            userToUpdate.setJob_title(user.getJob_title());
        }
        UserM updatedUser = userRepository.save(userToUpdate);
        System.out.println(updatedUser);
    }

    public void deleteUserById(long id) {
        Optional<UserM> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        UserM userToDelete = userOptional.get();
        userRepository.deleteById(id);
        System.out.println(userToDelete);
    }

    public List<UserM> getUserByJobTitle(String jobTitle) {
        return userRepository.findByJob_title(jobTitle);
    }
}
