package com.pajelonek.clipwatcher.configuration.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConditionalOnProperty(
        value = "twitch.auth.scheduler.enabled", havingValue = "true"
)
@Configuration
@EnableScheduling
public class SchedulingConfiguration {
}