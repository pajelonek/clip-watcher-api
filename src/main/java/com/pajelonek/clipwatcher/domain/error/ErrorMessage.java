package com.pajelonek.clipwatcher.domain.error;

import java.util.Date;

public record ErrorMessage(int statusCode, Date timestamp, String message, String description) { }
