package com.pajelonek.clipwatcher.domain.twitch.channels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    @JsonProperty("broadcaster_language")
    private String broadcasterLanguage;
    @JsonProperty("broadcaster_login")
    private String broadcasterLogin;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("game_name")
    private String gameName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("is_live")
    private boolean isLive;
    @JsonProperty("tag_ids")
    private List<String> tagIds;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("started_at")
    private String startedAt;
}