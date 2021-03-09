package com.company.backinstock.model;

public enum UserPriority {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private String priority;

    UserPriority(String priority) {
        this.priority = priority;
    }

    public static UserPriority priorityGet(String priority) {
        for (UserPriority level : UserPriority.values()) {
            if (level.priority.equals(priority)) {
                return level;
            }
        }
        throw new IllegalArgumentException("No such priority");
    }

    public String get() {
        return priority;
    }
}
