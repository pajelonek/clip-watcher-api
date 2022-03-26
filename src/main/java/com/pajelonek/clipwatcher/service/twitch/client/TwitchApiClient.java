package com.pajelonek.clipwatcher.service.twitch.client;

import com.pajelonek.clipwatcher.configuration.twitch.TwitchApiConfiguration;
import com.pajelonek.clipwatcher.configuration.twitch.TwitchCredentialsConfiguration;
import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.ClipsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(twitchCredentialsConfiguration.getBearer());

        String urlTemplate = TwitchApiUrlCreator.createClipsUrl(clipsRequest, twitchApiConfiguration.getClipsEndpoint());

        log.info("Sending request to twitch API to url {} with request {}", urlTemplate, clipsRequest);
        HttpEntity<ClipsResponse> clipsResponse =
                restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), ClipsResponse.class);
        log.info("Twitch Api responded with " + clipsResponse.getBody());
        return clipsResponse.getBody();
    }
}