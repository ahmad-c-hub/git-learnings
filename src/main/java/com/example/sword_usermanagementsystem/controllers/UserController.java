package com.example.sword_usermanagementsystem.controllers;

import com.example.sword_usermanagementsystem.models.UserM;
import com.example.sword_usermanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<UserM> getUsers() {
        return userService.getUsers();
    }


    @GetMapping("/{id}")
    public UserM getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-user")
    public void registerUser(@RequestBody UserM user) {
        userService.add(user);
        System.out.println(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserM user){
        System.out.println(user);
        return userService.verify(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public void updateUserById(@PathVariable("id") Long id, @RequestBody UserM user) {
        userService.updateUserById(id,user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") long id) {

        userService.deleteUserById(id);
    }
    @GetMapping("job/{job_title}")
    public List<UserM> getUserByJobTitle(@PathVariable("job_title") String jobTitle) {
        return userService.getUserByJobTitle(jobTitle);
    }
    @GetMapping("/usersd")
    public String Gibberish(){
        return "Gibberish";
    }
}
