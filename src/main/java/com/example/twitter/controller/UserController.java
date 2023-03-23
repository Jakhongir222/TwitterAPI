package com.example.twitter.controller;


import com.example.twitter.model.User;
import com.example.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    @PutMapping("/")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PutMapping("/follow")
    public Set<User> followUser (@RequestBody LinkedHashMap<String , String> body, String username){
        String loggedInUser = String.valueOf(userService.getUserByUserName(username));
        String followedUser = body.get("followedUser");
        return userService.followUser(loggedInUser, followedUser);

    }


}
