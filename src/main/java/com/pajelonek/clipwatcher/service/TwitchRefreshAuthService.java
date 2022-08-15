package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.configuration.twitch.TwitchCredentialsConfiguration;
import com.pajelonek.clipwatcher.domain.twitch.auth.RefreshAuthTokenResponse;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class TwitchRefreshAuthService {
    private final TwitchApiClient twitchApiClient;
    private final TwitchCredentialsConfiguration twitchCredentialsConfiguration;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public TwitchRefreshAuthService(TwitchApiClient twitchApiClient, TwitchCredentialsConfiguration twitchCredentialsConfiguration) {
        this.twitchApiClient = twitchApiClient;
        this.twitchCredentialsConfiguration = twitchCredentialsConfiguration;
    }

    @Scheduled(fixedRateString  = "${twitch.auth.scheduler.interval.inMillis}", initialDelay=3000)
    public void refreshTwitchAuthToken() {
        log.info("Refreshing twitch auth token started at {}", dateFormat.format(new Date()));
        RefreshAuthTokenResponse newAuthToken = twitchApiClient.getNewAuthToken();
        twitchCredentialsConfiguration.setBearer(newAuthToken.getAccessToken());
        log.info("Refreshing twitch auth token ended at {}", dateFormat.format(new Date()));
    }
}
