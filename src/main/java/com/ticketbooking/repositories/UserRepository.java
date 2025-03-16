package com.ticketbooking.repositories;

import com.ticketbooking.models.Customer;
import com.ticketbooking.models.User;

import java.util.List;

/**
 * Repository interface for User-related data operations.
 */
public interface UserRepository {
    boolean saveUser(User user);

    User getUser(String id);

    User getUserByEmail(String email);

    List<Customer> getAllCustomers();

    boolean updateUser(User user);

    boolean deleteUser(String id);
}