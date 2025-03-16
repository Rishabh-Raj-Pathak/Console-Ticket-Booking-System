package com.ticketbooking.models;

import com.ticketbooking.models.enums.AccountStatus;

/**
 * Base class for all users in the system.
 */
public abstract class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private AccountStatus status;

    // Constructor
    public User(String id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    // Method to verify password
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    // Method to reset password
    public boolean resetPassword(String oldPassword, String newPassword) {
        if (verifyPassword(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }
}