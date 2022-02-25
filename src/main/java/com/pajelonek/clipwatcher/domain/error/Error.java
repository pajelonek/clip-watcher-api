package com.pajelonek.clipwatcher.domain.error;

public enum Error {
    CLIPS_REQUIRED_MAIN_QUERY(5000, "Clips endpoint require one main parameter."),
    CLIPS_WRONG_FIRST_PARAMETER(5001, "First parameter must be between 0 and 100.");

    private final int id;
    private final String message;

    Error(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}