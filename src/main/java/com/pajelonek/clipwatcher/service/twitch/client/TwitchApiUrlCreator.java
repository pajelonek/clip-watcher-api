package com.pajelonek.clipwatcher.service.twitch.client;

import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesRequest;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
public class TwitchApiUrlCreator {

    private static final String FIRST_QUERY_PARAM = "first";
    private TwitchApiUrlCreator() {
        throw new IllegalStateException("Utility class");
    }

    public static String createClipsUrl(ClipsRequest clipsRequest, String clipsEndpoint) {
        log.info("Creating url for /clips endpoint {} with request {}", clipsEndpoint, clipsRequest);
        return UriComponentsBuilder.fromHttpUrl(clipsEndpoint)
                .queryParamIfPresent("game_id", Optional.ofNullable(clipsRequest.getGameId()))
                .queryParamIfPresent("broadcaster_id", Optional.ofNullable(clipsRequest.getBroadcasterId()))
                .queryParamIfPresent("id", Optional.ofNullable(clipsRequest.getClipId()))
                .queryParamIfPresent("after", Optional.ofNullable(clipsRequest.getAfter()))
                .queryParamIfPresent("before", Optional.ofNullable(clipsRequest.getBefore()))
                .queryParamIfPresent(FIRST_QUERY_PARAM, Optional.ofNullable(clipsRequest.getFirst()))
                .queryParamIfPresent("started_at", Optional.ofNullable(clipsRequest.getStartedAt()))
                .queryParamIfPresent("ended_at", Optional.ofNullable(clipsRequest.getEndedAt()))
                .toUriString();
    }

    public static String createTopCategoriesUrl(CategoriesRequest categoriesRequest, String categoriesEndpoint) {
        log.info("Creating url for /top/categories endpoint {} with request {}", categoriesEndpoint, categoriesRequest);
        return UriComponentsBuilder.fromHttpUrl(categoriesEndpoint)
                .queryParamIfPresent("after", Optional.ofNullable(categoriesRequest.getAfter()))
                .queryParamIfPresent("before", Optional.ofNullable(categoriesRequest.getBefore()))
                .queryParamIfPresent(FIRST_QUERY_PARAM, Optional.ofNullable(categoriesRequest.getFirst()))
                .toUriString();
    }

    public static String createSearchCategoriesUrl(String query, String categoriesEndpoint) {
        log.info("Creating url for /top/categories endpoint {} with query {}", categoriesEndpoint, query);
        return UriComponentsBuilder.fromHttpUrl(categoriesEndpoint)
                .queryParamIfPresent("query", Optional.ofNullable(query))
                .toUriString();
    }

    public static String createTopStreamUrl(String topStreamsEndpoint) {
        log.info("Creating url for /top/streams endpoint {}", topStreamsEndpoint);
        return UriComponentsBuilder.fromHttpUrl(topStreamsEndpoint)
                .queryParamIfPresent(FIRST_QUERY_PARAM, Optional.of(10))
                .toUriString();
    }

    public static String createSearchChannelsUrl(String query, String channelsEndpoint) {
        log.info("Creating url for /search/channels endpoint {} with query {}", channelsEndpoint, query);
        if (Optional.ofNullable(query).isEmpty()) {
            query = String.valueOf(' ');
        }
        return UriComponentsBuilder.fromHttpUrl(channelsEndpoint)
                .queryParamIfPresent("query", Optional.of(query))
                .toUriString();
    }

    public static String createRefreshAuthTokenUrl(String refreshAuthTokenEndpoint, String clientId, String clientSecret) {
        return UriComponentsBuilder.fromHttpUrl(refreshAuthTokenEndpoint)
                .queryParamIfPresent("client_id", Optional.of(clientId))
                .queryParamIfPresent("client_secret", Optional.of(clientSecret))
                .queryParamIfPresent("grant_type", Optional.of("client_credentials"))
                .toUriString();
    }
}
