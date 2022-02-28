package integration;

import com.pajelonek.clipwatcher.ClipWatcherApplication;
import com.pajelonek.clipwatcher.configuration.twitch.TwitchCredentialsConfiguration;
import com.pajelonek.clipwatcher.domain.error.DefaultException;
import com.pajelonek.clipwatcher.domain.error.Error;
import com.pajelonek.clipwatcher.domain.error.ErrorMessage;
import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.ClipsResponse;
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
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("LOCAL")
@ContextConfiguration(classes = {TwitchCredentialsConfiguration.class})
@Import({ClipWatcherApplication.class})
class ClipsIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String URL = "/clips";

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

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "{noop}password");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<ClipsRequest> entity = new HttpEntity<>(clipsRequest, headers);

        // when

        ResponseEntity<ErrorMessage> response = restTemplate.exchange(
                createURLWithPort(port),
                HttpMethod.POST, entity, ErrorMessage.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(httpStatus);
        assertThat(Objects.requireNonNull(response.getBody()).message()).isEqualTo(new DefaultException(error).getLocalizedMessage());
    }

    @Test
    void testHealthCheck() {

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/actuator/health", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }


    private String createURLWithPort(int port) {
        return "http://localhost:" + port + URL;
    }

    private static Stream<Arguments> clipsErrorHandlingMethodSource() {
        return Stream.of(
                arguments(null, null, null, "20", HttpStatus.BAD_REQUEST, Error.CLIPS_REQUIRED_MAIN_QUERY)
        );
    }
}