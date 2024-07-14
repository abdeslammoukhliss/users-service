package org.test.usersservice.services;

import org.test.usersservice.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User findUserByUsername(String username);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
