package com.learnings.capstone.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import java.util.Optional;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learnings.capstone.entity.Users;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        // Arrange
        String userName = "testUser";
        Users user = new Users();
        user.setName(userName);
        userRepository.save(user);

        // Act
        Optional<Users> foundUser = userRepository.findByName(userName);

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(userName, foundUser.get().getName());
    }

    @Test
    public void testExistsByName() {
        // Arrange
        String existingUserName = "existingUser";
        Users existingUser = new Users();
        existingUser.setName(existingUserName);
        userRepository.save(existingUser);

        String nonExistingUserName = "nonExistingUser";

        // Act
        boolean existingUserExists = userRepository.existsByName(existingUserName);
        boolean nonExistingUserExists = userRepository.existsByName(nonExistingUserName);

        // Assert
        assertTrue(existingUserExists);
        assertFalse(nonExistingUserExists);
    }
}

