package com.pajelonek.clipwatcher.service.twitch.client;

import com.pajelonek.clipwatcher.configuration.twitch.TwitchApiConfiguration;
import com.pajelonek.clipwatcher.configuration.twitch.TwitchCredentialsConfiguration;
import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.ClipsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Slf4j
public class TwitchApiClient {

    private final TwitchApiConfiguration twitchApiConfiguration;

    private final TwitchCredentialsConfiguration twitchCredentialsConfiguration;

    private final RestTemplate restTemplate;

    public TwitchApiClient(TwitchApiConfiguration twitchApiConfiguration, TwitchCredentialsConfiguration twitchCredentialsConfiguration, RestTemplate restTemplate) {
        this.twitchApiConfiguration = twitchApiConfiguration;
        this.twitchCredentialsConfiguration = twitchCredentialsConfiguration;
        this.restTemplate = restTemplate;
    }

    public ClipsResponse clips(ClipsRequest clipsRequest) {
        log.info("Calling twitch api for endpoint /clips");

        HttpHeaders headers = new HttpHeaders();
        headers.put("Client-Id", Collections.singletonList(twitchCredentialsConfiguration.getClientId()));
        headers.put("game_id", Collections.singletonList(clipsRequest.getGameId()));
        headers.setBearerAuth(twitchCredentialsConfiguration.getBearer());

        HttpEntity<ClipsResponse> clipsResponse =
                restTemplate.exchange(twitchApiConfiguration.getClipsEndpoint() + "?game_id="+clipsRequest.getGameId(), HttpMethod.GET,  new HttpEntity<>(headers), ClipsResponse.class);
        return clipsResponse.getBody();
    }
}
