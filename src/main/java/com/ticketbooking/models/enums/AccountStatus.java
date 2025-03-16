package com.ticketbooking.models.enums;

/**
 * Represents the possible statuses of a user account in the system.
 */
public enum AccountStatus {
    ACTIVE, // Account is active and can be used
    BLOCKED, // Account is temporarily blocked
    BANNED, // Account is permanently banned
    ARCHIVED // Account is archived (inactive but preserved)
}