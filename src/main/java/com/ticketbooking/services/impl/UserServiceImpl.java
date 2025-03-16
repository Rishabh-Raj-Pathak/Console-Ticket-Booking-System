package com.ticketbooking.services.impl;

import com.ticketbooking.models.Admin;
import com.ticketbooking.models.Customer;
import com.ticketbooking.models.User;
import com.ticketbooking.models.enums.AccountStatus;
import com.ticketbooking.repositories.UserRepository;
import com.ticketbooking.services.UserService;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of UserService interface.
 */
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Customer createCustomer(String name, String email, String phone, String password) {
        // Check if email already exists
        if (userRepository.getUserByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        String id = UUID.randomUUID().toString();
        Customer customer = new Customer(id, name, email, phone, password);
        userRepository.saveUser(customer);
        return customer;
    }

    @Override
    public Admin createAdmin(String name, String email, String phone, String password) {
        // Check if email already exists
        if (userRepository.getUserByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        String id = UUID.randomUUID().toString();
        Admin admin = new Admin(id, name, email, phone, password);
        userRepository.saveUser(admin);
        return admin;
    }

    @Override
    public User getUser(String id) {
        return userRepository.getUser(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return userRepository.getAllCustomers();
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public boolean changeUserStatus(String userId, AccountStatus status) {
        User user = userRepository.getUser(userId);
        if (user == null) {
            return false;
        }

        user.setStatus(status);
        return userRepository.updateUser(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user != null && user.verifyPassword(password)) {
            return user;
        }
        return null;
    }
}