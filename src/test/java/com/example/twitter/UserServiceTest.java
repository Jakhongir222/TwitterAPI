package com.example.twitter;

import com.example.twitter.model.User;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void testUser() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String username = "johndoe";
        String password = "p@ssw0rd";

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        User user1 = new User(null, "John", "Doe", "john@example.com", "user1", "password", null);
        User user2 = new User(null, "Jane", "Doe", "jane@example.com", "user2", "password", null);
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertThat(result).isEqualTo(users);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.updateUser(user);

        // Assert
        assertThat(result).isEqualTo(user);
    }

}
