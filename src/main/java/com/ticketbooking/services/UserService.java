package com.ticketbooking.services;

import com.ticketbooking.models.Admin;
import com.ticketbooking.models.Customer;
import com.ticketbooking.models.User;
import com.ticketbooking.models.enums.AccountStatus;

import java.util.List;

/**
 * Interface for user-related operations.
 */
public interface UserService {
    Customer createCustomer(String name, String email, String phone, String password);

    Admin createAdmin(String name, String email, String phone, String password);

    User getUser(String id);

    List<Customer> getAllCustomers();

    boolean updateUser(User user);

    boolean deleteUser(String id);

    boolean changeUserStatus(String userId, AccountStatus status);

    User login(String email, String password);
}