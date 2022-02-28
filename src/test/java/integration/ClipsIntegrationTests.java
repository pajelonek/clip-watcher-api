package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pajelonek.clipwatcher.ClipWatcherApplication;
import com.pajelonek.clipwatcher.configuration.twitch.TwitchCredentialsConfiguration;
import com.pajelonek.clipwatcher.domain.error.DefaultException;
import com.pajelonek.clipwatcher.domain.error.Error;
import com.pajelonek.clipwatcher.domain.error.ErrorMessage;
import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.ClipsResponse;
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
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("LOCAL")
@ContextConfiguration(classes = {TwitchCredentialsConfiguration.class})
@Import({ClipWatcherApplication.class})
class ClipsIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "/clips";

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

        mockServer.expect(ExpectedCount.manyTimes(), requestTo("https://api.twitch.tv/helix/clips?game_id=dummy"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonString("payloads/correctTwitchClipsResponse.json", ClipsResponse.class)));

        // when
        ResponseEntity<ClipsResponse> response = testRestTemplate.exchange(
                createURLWithPort(port),
                HttpMethod.POST, new HttpEntity<>(clipsRequest), ClipsResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(readFromFile("payloads/correctTwitchClipsResponse.json", ClipsResponse.class));

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
                createURLWithPort(port),
                HttpMethod.POST, new HttpEntity<>(clipsRequest), ErrorMessage.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(httpStatus);
        assertThat(Objects.requireNonNull(response.getBody()).message()).isEqualTo(new DefaultException(error).getLocalizedMessage());
    }

    private String createURLWithPort(int port) {
        return "http://localhost:" + port + URL;
    }

    private static Stream<Arguments> clipsErrorHandlingMethodSource() {
        return Stream.of(
                arguments(null, null, null, "20", HttpStatus.BAD_REQUEST, Error.CLIPS_REQUIRED_MAIN_QUERY)
        );
    }

    private static String jsonString(String path, Class neededClass) throws IOException {
        return new ObjectMapper().writeValueAsString(new ObjectMapper().readValue(new ClassPathResource(path).getFile(), neededClass));
    }

    private static Object readFromFile(String path, Class neededClass) throws IOException {
        return new ObjectMapper().readValue(new ClassPathResource(path).getFile(), neededClass);

    }

}