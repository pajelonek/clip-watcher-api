package com.pajelonek.clipwatcher.configuration.twitch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "twitch.api")
@Getter
@Setter
public class TwitchApiConfiguration {

    private String clipsEndpoint;

    private String topStreamsEndpoint;

    private String topCategoriesEndpoint;

    private String searchCategoriesEndpoint;

    private String searchChannelsEndpoint;

    private String tokenEndpoint;

    private String tokenGrantType;

    private String gamesEndpoint;

}
