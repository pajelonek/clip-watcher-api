package com.pajelonek.clipwatcher.service.twitch.client;

import com.pajelonek.clipwatcher.configuration.twitch.TwitchApiConfiguration;
import com.pajelonek.clipwatcher.configuration.twitch.TwitchCredentialsConfiguration;
import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesRequest;
import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesResponse;
import com.pajelonek.clipwatcher.domain.twitch.channels.ChannelsResponse;
import com.pajelonek.clipwatcher.domain.twitch.streams.TopStreamsResponse;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsResponse;
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

    private static final String CLIENT_ID_HEADER = "Client-Id";

    public TwitchApiClient(TwitchApiConfiguration twitchApiConfiguration, TwitchCredentialsConfiguration twitchCredentialsConfiguration, RestTemplate restTemplate) {
        this.twitchApiConfiguration = twitchApiConfiguration;
        this.twitchCredentialsConfiguration = twitchCredentialsConfiguration;
        this.restTemplate = restTemplate;
    }

    public ClipsResponse clips(ClipsRequest clipsRequest) {
        log.info("Calling twitch api for endpoint /clips");

        HttpHeaders headers = new HttpHeaders();
        headers.put(CLIENT_ID_HEADER, Collections.singletonList(twitchCredentialsConfiguration.getClientId()));
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(twitchCredentialsConfiguration.getBearer());

        String urlTemplate = TwitchApiUrlCreator.createClipsUrl(clipsRequest, twitchApiConfiguration.getClipsEndpoint());

        log.info("Sending request to twitch API to url {} with request {}", urlTemplate, clipsRequest);
        HttpEntity<ClipsResponse> clipsResponse =
                restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), ClipsResponse.class);
        log.info("Twitch Api clips responded with " + clipsResponse.getBody());
        return clipsResponse.getBody();
    }

    public TopStreamsResponse topStreams() {
        log.info("Calling twitch api for endpoint /top/streams");

        HttpHeaders headers = new HttpHeaders();
        headers.put(CLIENT_ID_HEADER, Collections.singletonList(twitchCredentialsConfiguration.getClientId()));
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(twitchCredentialsConfiguration.getBearer());

        String urlTemplate = TwitchApiUrlCreator.createTopStreamUrl(twitchApiConfiguration.getTopStreamsEndpoint());
        log.info("Sending request to twitch API streams to url {}", urlTemplate);
        HttpEntity<TopStreamsResponse> topStreamsResponse =
                restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), TopStreamsResponse.class);
        log.info("Twitch Api Top Streams responded with " + topStreamsResponse.getBody());
        return topStreamsResponse.getBody();
    }

    public CategoriesResponse topCategories(CategoriesRequest categoriesRequest) {
        log.info("Calling twitch api for endpoint /top/categories");

        HttpHeaders headers = new HttpHeaders();
        headers.put(CLIENT_ID_HEADER, Collections.singletonList(twitchCredentialsConfiguration.getClientId()));
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(twitchCredentialsConfiguration.getBearer());

        String urlTemplate = TwitchApiUrlCreator.createTopCategoriesUrl(categoriesRequest, twitchApiConfiguration.getTopCategoriesEndpoint());
        log.info("Sending request to twitch API Top Categories to url {}", urlTemplate);
        HttpEntity<CategoriesResponse> topCategoriesResponse =
                restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), CategoriesResponse.class);
        log.info("Twitch Api Categories responded with " + topCategoriesResponse.getBody());
        return topCategoriesResponse.getBody();
    }

    public CategoriesResponse searchCategories(String query) {
        log.info("Calling twitch api for endpoint /categories/search");

        HttpHeaders headers = new HttpHeaders();
        headers.put(CLIENT_ID_HEADER, Collections.singletonList(twitchCredentialsConfiguration.getClientId()));
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(twitchCredentialsConfiguration.getBearer());

        String urlTemplate = TwitchApiUrlCreator.createSearchCategoriesUrl(query, twitchApiConfiguration.getSearchCategoriesEndpoint());
        log.info("Sending request to twitch API to url {}", urlTemplate);
        HttpEntity<CategoriesResponse> searchCategoriesResponse =
                restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), CategoriesResponse.class);
        log.info("Twitch Api responded with " + searchCategoriesResponse.getBody());
        return searchCategoriesResponse.getBody();
    }

    public ChannelsResponse searchChannels(String query) {
        log.info("Calling twitch api for endpoint /channels/search");

        HttpHeaders headers = new HttpHeaders();
        headers.put(CLIENT_ID_HEADER, Collections.singletonList(twitchCredentialsConfiguration.getClientId()));
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setBearerAuth(twitchCredentialsConfiguration.getBearer());

        String urlTemplate = TwitchApiUrlCreator.createSearchChannelsUrl(query, twitchApiConfiguration.getSearchChannelsEndpoint());
        log.info("Sending request to twitch API to url {}", urlTemplate);
        HttpEntity<ChannelsResponse> searchChannelsResponse =
                restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), ChannelsResponse.class);
        log.info("Twitch Api responded with " + searchChannelsResponse.getBody());
        return searchChannelsResponse.getBody();
    }
}