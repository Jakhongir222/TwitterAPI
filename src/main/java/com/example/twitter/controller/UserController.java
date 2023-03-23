package com.example.twitter.controller;

import com.example.twitter.model.User;
import com.example.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
    }

    @PutMapping("/")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/{username}")
    ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUserName(username));
    }

    @PostMapping("/{follower}/follow/{following}")
    public ResponseEntity<String> followUser(@PathVariable("follower") String follower, @PathVariable("following") String following) {
        userService.followUser(follower, following);
        return ResponseEntity.ok("User followed successfully");
    }

    @PostMapping("/{follower}/unfollow/{following}")
    public ResponseEntity<String> unfollowUser(@PathVariable("follower") String follower, @PathVariable("following") String following) {
        userService.unfollowUser(follower, following);
        return ResponseEntity.ok("User unfollowed successfully");
    }

    @GetMapping("/{username}/following")
    ResponseEntity<List<User>> getFollowing(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getFollowing(username));
    }
}
