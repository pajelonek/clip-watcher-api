package com.pajelonek.clipwatcher.domain.twitch;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
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
