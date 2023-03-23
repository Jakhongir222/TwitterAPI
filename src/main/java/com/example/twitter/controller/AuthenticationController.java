package com.example.twitter.controller;

import com.example.twitter.exceptions.EmailAlreadyTakenException;
import com.example.twitter.exceptions.UserDoesNotExistException;
import com.example.twitter.model.RegistrationObject;
import com.example.twitter.model.User;
import com.example.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken() {
        return new ResponseEntity<String>("The email you provided is already in use", HttpStatus.CONFLICT);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationObject object) {
        return userService.registerUser(object);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesntExist() {
        return new ResponseEntity<String>("The user you are looking for does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/phone")
    public User updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body) {
        String username = body.get("username");
        String phone = body.get("phone");
        User user = userService.getUserByUserName(username);
        user.setPhone(phone);

        return userService.updateUser(user);
    }

    @PostMapping("email")
    public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body) {
        userService.generateEmailVerification(body.get("username"));
        return new ResponseEntity<String>("Verification code generated , email has been sent", HttpStatus.OK);
    }
}
