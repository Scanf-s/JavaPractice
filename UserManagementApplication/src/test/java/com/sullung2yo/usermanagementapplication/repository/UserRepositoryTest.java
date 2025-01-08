package com.sullung2yo.usermanagementapplication.repository;

import com.sullung2yo.usermanagementapplication.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.sullung2yo.usermanagementapplication.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepository = UserRepository.getInstance();

    @AfterEach
    void tearDown() {
        userRepository.clearStorage();
    }

    @Test
    void save() {
        //given
        User user = new User("test", 123);

        //when
        User savedUser = userRepository.save(user);

        //then
        User findUser = userRepository.findById(user.getId());
        assertThat(findUser).isEqualTo(savedUser);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        //given
        for(int i = 0; i < 10; i++){
           User user = new User("test " + i, i);
           userRepository.save(user);
        }

        //when
        List<User> users = userRepository.findAll();

        //then
        assertThat(users.size()).isEqualTo(10);
        assertThat(users.getFirst()).isInstanceOf(User.class);
    }

    @Test
    void clearStorage() {
    }
}