package com.pajelonek.clipwatcher.service;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = { "twitch.auth.scheduler.enabled=true" })
@DirtiesContext
class TwitchRefreshAuthServiceTest {

    @SpyBean
    private TwitchRefreshAuthService twitchRefreshAuthService;

    @Test
    void whenApplicationStart_thenSchedulerShouldRefreshToken() {
        await()
                .atMost(Duration.FIVE_SECONDS)
                .untilAsserted(() -> verify(twitchRefreshAuthService, atLeast(1)).refreshTwitchAuthToken());
    }

    @Test
    void whenApplicationStart_thenSchedulerShouldRefreshTokenAndRefreshAfter2Seconds() {
        await()
                .atMost(Duration.TEN_SECONDS)
                .untilAsserted(() -> verify(twitchRefreshAuthService, atLeast(2)).refreshTwitchAuthToken());
    }
}
