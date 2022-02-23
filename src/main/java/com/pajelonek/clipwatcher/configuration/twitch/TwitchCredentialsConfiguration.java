package com.pajelonek.clipwatcher.configuration.twitch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitch.credentials")
@EnableConfigurationProperties
@Getter
@Setter
public class TwitchCredentialsConfiguration {

    private String clientId;

    private String clientSecret;

    private String bearer;
}
