package com.ticketbooking.repositories.impl;

import com.ticketbooking.models.Customer;
import com.ticketbooking.models.User;
import com.ticketbooking.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of UserRepository.
 * This is a simple implementation for learning purposes.
 * In a real application, this would connect to a database.
 */
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();
    private Map<String, User> usersByEmail = new HashMap<>();

    @Override
    public boolean saveUser(User user) {
        users.put(user.getId(), user);
        usersByEmail.put(user.getEmail(), user);
        return true;
    }

    @Override
    public User getUser(String id) {
        return users.get(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return usersByEmail.get(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return users.values().stream()
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateUser(User user) {
        // If email has changed, update the email index
        User existingUser = users.get(user.getId());
        if (existingUser != null && !existingUser.getEmail().equals(user.getEmail())) {
            usersByEmail.remove(existingUser.getEmail());
            usersByEmail.put(user.getEmail(), user);
        }

        users.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean deleteUser(String id) {
        User user = users.get(id);
        if (user != null) {
            users.remove(id);
            usersByEmail.remove(user.getEmail());
            return true;
        }
        return false;
    }
}