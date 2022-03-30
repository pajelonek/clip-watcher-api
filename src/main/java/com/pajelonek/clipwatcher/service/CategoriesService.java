package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesRequest;
import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesResponse;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoriesService {

    private final TwitchApiClient twitchApiClient;

    public CategoriesService(TwitchApiClient twitchApiClient) {
        this.twitchApiClient = twitchApiClient;
    }

    public ResponseEntity<CategoriesResponse> topCategories(CategoriesRequest categoriesRequest) {
        log.info("Incoming POST request for /category/top");
        CategoriesResponse categoriesResponse = twitchApiClient.topCategories(categoriesRequest);
        return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
    }

    public ResponseEntity<CategoriesResponse> searchCategories(String query) {
        log.info("Incoming POST request for /category/search with query {}", query);
        CategoriesResponse categoriesResponse = twitchApiClient.searchCategories(query);
        return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
    }

}
