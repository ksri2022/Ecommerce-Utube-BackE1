package com.youtube.ecommerce.controller;


import com.youtube.ecommerce.entity.User;
import com.youtube.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUser() {
        userService.initRolesAndUser();

    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);

    }



//    @PostMapping({"/registerNewUser"})
//    public User registerNewUser(@RequestBody User user){
//            return userService.registerNewUser(user);
//    }

    @GetMapping({"/forAdmin" })
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to admin";

    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){

        return "This URL is only accessible to user";
    }



}
