package com.pajelonek.clipwatcher.controller;

import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesRequest;
import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesResponse;
import com.pajelonek.clipwatcher.domain.twitch.channels.ChannelsResponse;
import com.pajelonek.clipwatcher.domain.twitch.streams.TopStreamsResponse;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsResponse;
import com.pajelonek.clipwatcher.service.CategoriesService;
import com.pajelonek.clipwatcher.service.ChannelsService;
import com.pajelonek.clipwatcher.service.StreamsService;
import com.pajelonek.clipwatcher.service.ClipsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TwitchController {

    private final ClipsService clipsService;

    private final StreamsService streamsService;

    private final CategoriesService categoryService;

    private final ChannelsService channelsService;

    public TwitchController(ClipsService clipsService, StreamsService streamsService, CategoriesService categoryService, ChannelsService channelsService) {
        this.clipsService = clipsService;
        this.streamsService = streamsService;
        this.categoryService = categoryService;
        this.channelsService = channelsService;
    }

    @PostMapping(value = "/clips", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClipsResponse> clips(@RequestBody ClipsRequest clipsRequest) {
        return clipsService.clips(clipsRequest);
    }

    @GetMapping(value = "/streams/top", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopStreamsResponse> topStreams() {
        return streamsService.topStreams();
    }

    @PostMapping(value = "/categories/top", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesResponse> topCategories(@RequestBody CategoriesRequest categoriesRequest) {
        return categoryService.topCategories(categoriesRequest);
    }

    @GetMapping(value = "/categories/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesResponse> searchCategories(@RequestParam String query) {
        return categoryService.searchCategories(query);
    }

    @GetMapping(value = "/channels/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChannelsResponse> searchChannels(@RequestParam String query) {
        return channelsService.searchChannel(query);
    }

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("{'status' : 'up'}");
    }
}
