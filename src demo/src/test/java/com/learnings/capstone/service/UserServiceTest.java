package com.learnings.capstone.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.learnings.capstone.entity.Users;
import com.learnings.capstone.exception.CatalogBusinessException;
import com.learnings.capstone.repository.UserRepository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testAuthenticateUserFoundAndPasswordMatch() {
        // Arrange
        String username = "testUser";
        String password = "password";
        Users user = new Users();
        user.setName(username);
        user.setPassword(password);

        when(userRepository.findByName(username)).thenReturn(Optional.of(user));

        // Act
        Optional<Users> result = userService.authenticate(username, password);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test(expected = CatalogBusinessException.class)
    public void testAuthenticateUserNotFound() {
        // Arrange
        String username = "nonExistentUser";

        when(userRepository.findByName(username)).thenReturn(Optional.empty());

        // Act
        userService.authenticate(username, "password");
        // Assert: CatalogBusinessException should be thrown
    }

    @Test
    public void testAuthenticateUserFoundAndPasswordMismatch() {
        // Arrange
        String username = "testUser";
        String password = "password";
        Users user = new Users();
        user.setName(username);
        user.setPassword("differentPassword");

        when(userRepository.findByName(username)).thenReturn(Optional.of(user));

        // Act
        Optional<Users> result = userService.authenticate(username, password);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateUser() {
        // Arrange
        Users user = new Users();
        user.setName("testUser");
        user.setPassword("password");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        Users createdUser = userService.create(user);

        // Assert
        assertEquals(user, createdUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetById() {
        // Arrange
        long userId = 1L;
        Users user = new Users();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<Users> result = userService.getById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testGetByName() {
        // Arrange
        String username = "testUser";
        Users user = new Users();
        user.setName(username);

        when(userRepository.findByName(username)).thenReturn(Optional.of(user));

        // Act
        Optional<Users> result = userService.getByName(username);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
}

