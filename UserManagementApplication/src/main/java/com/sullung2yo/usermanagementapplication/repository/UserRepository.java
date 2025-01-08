package com.sullung2yo.usermanagementapplication.repository;

import com.sullung2yo.usermanagementapplication.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    private static Map<Long, User> storage = new ConcurrentHashMap<>(); // ConcurrentHashMap is thread-safe
    private static long sequence = 0L;

    private static final UserRepository instance = new UserRepository();

    private UserRepository() {}

    public static UserRepository getInstance() {
        return instance;
    }

    public User save(User user) {
        user.setId(++sequence);
        storage.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return storage.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void clearStorage() {
        storage.clear();
    }
}
