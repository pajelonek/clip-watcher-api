package com.pajelonek.clipwatcher.domain.twitch.clips;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClipsRequest {

    private String gameId;

    private String broadcasterId;

    private String clipId;

    private String after;

    private String before;

    private String endedAt;

    private String first;

    private String startedAt;
}
