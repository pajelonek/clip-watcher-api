package com.pajelonek.clipwatcher.service.twitch.client;

import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@RunWith(SpringRunner.class)
class TwitchApiUrlCreatorTest {

    @ParameterizedTest
    @MethodSource("clipsCreatorArgumentMethodSource")
    void testCreateClipsUrl(ClipsRequest clipsRequest, String twitchApiUrl, String expected){
        // given & when
        String createdUrl = TwitchApiUrlCreator.createClipsUrl(clipsRequest, twitchApiUrl);

        // then
        assertThat(createdUrl).isEqualTo(expected);
    }

    @Test
    void testCreateTopStreamsUrl(){
        // given
        String twitchApiUrl = "http://localhost/top/games";
        String expected = "http://localhost/top/games?first=10";

        // when
        String createdUrl = TwitchApiUrlCreator.createTopStreamUrl(twitchApiUrl);

        // then
        assertThat(createdUrl).isEqualTo(expected);
    }

    private static Stream<Arguments> clipsCreatorArgumentMethodSource() {
        return Stream.of(
                arguments(ClipsRequest.builder()
                        .clipId("123")
                        .broadcasterId(null)
                        .gameId(null)
                        .build(), "http://localhost", "http://localhost?id=123"),
                arguments(ClipsRequest.builder()
                        .clipId(null)
                        .broadcasterId("123")
                        .gameId(null)
                        .build(), "http://localhost", "http://localhost?broadcaster_id=123"),
                arguments(ClipsRequest.builder()
                        .clipId(null)
                        .broadcasterId(null)
                        .gameId("123")
                        .build(), "http://localhost", "http://localhost?game_id=123"),
                arguments(ClipsRequest.builder()
                        .clipId(null)
                        .broadcasterId(null)
                        .gameId("123")
                        .first("20")
                        .build(), "http://localhost", "http://localhost?game_id=123&first=20")
        );
    }
}
