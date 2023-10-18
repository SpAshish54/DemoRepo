package com.learnings.capstone.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class UsersTest {

    @Test
    public void testUsersConstructorAndGetters() {
        // Arrange
        long id = 1L;
        String name = "TestUser";
        String password = "TestPassword";
        String role = "TestRole";

        // Act
        Users user = new Users();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    public void testUsersSetters() {
        // Arrange
        Users user = new Users();
        long id = 1L;
        String name = "TestUser";
        String password = "TestPassword";
        String role = "TestRole";

        // Act
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }
}

