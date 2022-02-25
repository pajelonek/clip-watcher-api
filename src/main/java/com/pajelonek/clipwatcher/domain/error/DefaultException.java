package com.pajelonek.clipwatcher.domain.error;

import java.io.Serial;

public class DefaultException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 100L;

    public DefaultException(Error error) {
        super("#[" + error.getId() + "]: " + error.getMessage());
    }
}