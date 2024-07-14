package org.test.usersservice;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.usersservice.entities.User;
import org.test.usersservice.repositories.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");

        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("john_doe");
    }
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("qsdk@lskjd.com");
        userRepository.save(user);
        user.setUsername("abdo");
        user.setEmail("abdo@gmail.com");
        User updatedUser = userRepository.save(user);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUsername()).isEqualTo("abdo");
        assertThat(updatedUser.getEmail()).isEqualTo("abdo@gmail.com");

    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("john_doe");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("john@example.com");
    }
    @Test
    public void testFindById() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("abdo@gmail.com");
        userRepository.save(user);
        User foundUser = userRepository.findById(user.getId()).get();
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());

    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("abdo@gmail.com");
        userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("john_doe2");
        user2.setEmail("abd2o@gmail.com");
        userRepository.save(user2);
        User user3 = new User();
        user3.setUsername("john_doe3");
        user3.setEmail("azaz@gmail.Com");
        userRepository.save(user3);

        Iterable<User> users = userRepository.findAll();
        assertThat(users).hasSize(3).contains(user1, user2, user3);


    }

    @Test
    public void testUserNotFound() {
        User foundUser = userRepository.findByUsername("unknown_user");
        assertThat(foundUser).isNull();
    }
    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("klj@lkfs.com");
        userRepository.save(user);
        userRepository.delete(user);
        User foundUser = userRepository.findByUsername("john_doe");
        assertThat(foundUser).isNull();
    }
}
