package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pajelonek.clipwatcher.ClipWatcherApplication;
import com.pajelonek.clipwatcher.domain.error.DefaultException;
import com.pajelonek.clipwatcher.domain.error.Error;
import com.pajelonek.clipwatcher.domain.error.ErrorMessage;
import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesRequest;
import com.pajelonek.clipwatcher.domain.twitch.categories.CategoriesResponse;
import com.pajelonek.clipwatcher.domain.twitch.channels.ChannelsResponse;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsResponse;
import com.pajelonek.clipwatcher.domain.twitch.streams.TopStreamsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@ActiveProfiles("LOCAL")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ClipWatcherApplication.class})
class IntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private static final String clipsURL = "/clips";

    private static final String topStreamsURL = "/streams/top";

    private static final String topGamesURL = "/categories/top";

    private static final String searchGamesURL = "/categories/search";

    private static final String searchChannelsURL = "/channels/search";


    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(this.restTemplate);
    }

    @Test
    void testClipsResponseWithGameId() throws IOException {
        // given
        ClipsRequest clipsRequest = ClipsRequest.builder()
                .clipId(null)
                .broadcasterId(null)
                .gameId("dummy")
                .build();

        mockServer.expect(ExpectedCount.once(), requestTo("https://api.twitch.tv/helix/clips?game_id=dummy"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonString("payloads/correctTwitchClipsResponse.json", ClipsResponse.class)));

        // when
        ResponseEntity<ClipsResponse> response = testRestTemplate.exchange(
                createClipsURLWithPort(port),
                HttpMethod.POST, new HttpEntity<>(clipsRequest), ClipsResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(readFromFile("payloads/correctTwitchClipsResponse.json", ClipsResponse.class));

    }

    @Test
    void testTopStreamsResponse() throws IOException {
        // given
        mockServer.expect(ExpectedCount.once(), requestTo("https://api.twitch.tv/helix/streams"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonString("payloads/correctTwitchTopStreamsResponse.json", TopStreamsResponse.class)));

        // when
        ResponseEntity<TopStreamsResponse> response = testRestTemplate.exchange(
                createTopStreamsURLWithPort(port),
                HttpMethod.GET, null, TopStreamsResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(readFromFile("payloads/correctTwitchTopStreamsResponse.json", TopStreamsResponse.class));

    }

    @Test
    void testTopGamesResponse() throws IOException {
        // given
        CategoriesRequest categoriesRequest = CategoriesRequest.builder()
                .first("20")
                .build();

        mockServer.expect(ExpectedCount.once(), requestTo("https://api.twitch.tv/helix/games/top?first=20"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonString("payloads/correctTwitchTopGamesResponse.json", CategoriesResponse.class)));

        // when
        ResponseEntity<CategoriesResponse> response = testRestTemplate.exchange(
                createTopGamesURLWithPort(port),
                HttpMethod.POST, new HttpEntity<>(categoriesRequest), CategoriesResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(readFromFile("payloads/correctTwitchTopGamesResponse.json", CategoriesResponse.class));

    }

    @Test
    void testSearchCategoriesResponse() throws IOException {
        // given
        String query = "league";

        mockServer.expect(ExpectedCount.once(), requestTo("https://api.twitch.tv/helix/search/categories?query=" + query))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonString("payloads/correctTwitchSearchGamesResponseQueryLeague.json", CategoriesResponse.class)));

        // when
        ResponseEntity<CategoriesResponse> response = testRestTemplate.exchange(
                createSearchGamesURLWithPort(port, query),
                HttpMethod.GET, null, CategoriesResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(readFromFile("payloads/correctTwitchSearchGamesResponseQueryLeague.json", CategoriesResponse.class));

    }

    @Test
    void testSearchChannelsResponse() throws IOException {
        // given
        String query = "h2p";

        mockServer.expect(ExpectedCount.once(), requestTo("https://api.twitch.tv/helix/search/channels?query=" + query))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonString("payloads/correctTwitchSearchChannelsResponseQueryH2p.json", ChannelsResponse.class)));

        // when
        ResponseEntity<ChannelsResponse> response = testRestTemplate.exchange(
                createSearchChannelsURLWithPort(port, query),
                HttpMethod.GET, null, ChannelsResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(readFromFile("payloads/correctTwitchSearchChannelsResponseQueryH2p.json", ChannelsResponse.class));

    }

    @ParameterizedTest
    @MethodSource("clipsErrorHandlingMethodSource")
    void errorHandlingIntegrationTests(String clipId, String broadcasterId, String gameId, String first, HttpStatus httpStatus, Error error) {
        // given
        ClipsRequest clipsRequest = ClipsRequest.builder()
                .clipId(clipId)
                .broadcasterId(broadcasterId)
                .gameId(gameId)
                .first(first)
                .build();

        // when
        ResponseEntity<ErrorMessage> response = testRestTemplate.exchange(
                createClipsURLWithPort(port),
                HttpMethod.POST, new HttpEntity<>(clipsRequest), ErrorMessage.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(httpStatus);
        assertThat(Objects.requireNonNull(response.getBody()).message()).isEqualTo(new DefaultException(error).getLocalizedMessage());
    }

    private String createClipsURLWithPort(int port) {
        return "http://localhost:" + port + clipsURL;
    }

    private String createTopStreamsURLWithPort(int port) {
        return "http://localhost:" + port + topStreamsURL;
    }

    private String createTopGamesURLWithPort(int port) {
        return "http://localhost:" + port + topGamesURL;
    }

    private String createSearchGamesURLWithPort(int port, String query) {
        return "http://localhost:" + port + searchGamesURL + "?query=" + query;
    }

    private String createSearchChannelsURLWithPort(int port, String query) {
        return "http://localhost:" + port + searchChannelsURL + "?query=" + query;
    }

    private static Stream<Arguments> clipsErrorHandlingMethodSource() {
        return Stream.of(
                arguments(null, null, null, "20", HttpStatus.BAD_REQUEST, Error.CLIPS_REQUIRED_MAIN_QUERY),
                arguments("dummy1", "dummy2", "dummy3", null, HttpStatus.BAD_REQUEST, Error.CLIPS_MORE_THAN_ONE_MAIN_QUERY_PROVIDED)
        );
    }

    private static String jsonString(String path, Class neededClass) throws IOException {
        return new ObjectMapper().writeValueAsString(new ObjectMapper().readValue(new ClassPathResource(path).getFile(), neededClass));
    }

    private static Object readFromFile(String path, Class neededClass) throws IOException {
        return new ObjectMapper().readValue(new ClassPathResource(path).getFile(), neededClass);

    }

}