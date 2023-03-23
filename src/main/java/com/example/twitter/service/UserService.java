package com.example.twitter.service;

import com.example.twitter.exceptions.EmailAlreadyTakenException;
import com.example.twitter.exceptions.UserDoesNotExistException;
import com.example.twitter.model.RegistrationObject;
import com.example.twitter.model.Role;
import com.example.twitter.model.User;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User getUserByUserName(String username) {
        return userRepo.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }

    public User updateUser(User user) {
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

    public void followUser(String followerUsername, String followingUsername) {
        User follower = getUserByUserName(followerUsername);
        User following = getUserByUserName(followingUsername);
        follower.getFollowing().add(following);
        following.getFollowers().add(follower);
        userRepo.save(follower);
        userRepo.save(following);
    }

    public void unfollowUser(String followerUsername, String followingUsername) {
        User follower = getUserByUserName(followerUsername);
        User following = getUserByUserName(followingUsername);
        follower.getFollowing().remove(following);
        following.getFollowers().remove(follower);
        userRepo.save(follower);
        userRepo.save(following);
    }

    public List<User> getFollowing(String username) {
        User user = getUserByUserName(username);
        Set<User> following = user.getFollowing();
        return new ArrayList<>(following);
    }

    public User registerUser(RegistrationObject object) {

        User user = new User();
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setEmail(object.getEmail());

        String name = user.getFirstName() + user.getLastName();

        boolean nameTaken = true;
        String tempName = "";
        while (nameTaken) {
            tempName = generateUsername(name);
            if (userRepo.findByUsername(tempName).isEmpty()) {
                nameTaken = false;
            }
        }
        user.setUsername(tempName);

        Set<Role> roles = user.getAuthorities();
        roles.add(roleRepo.findRoleByAuthority("USER").get());
        user.setAuthorities(roles);

        try {
            return userRepo.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

    public void generateEmailVerification(String username) {

        User user = userRepo.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        user.setVerification(generateVerificationNumber());
        userRepo.save(user);
    }

    private String generateUsername(String name) {
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return name + generatedNumber;
    }

    private Long generateVerificationNumber() {
        return (long) Math.floor(Math.random() * 100_000_000);
    }

    public User getUserById(Integer userId) {
        return userRepo.findById(userId).orElse(null);
    }
}
