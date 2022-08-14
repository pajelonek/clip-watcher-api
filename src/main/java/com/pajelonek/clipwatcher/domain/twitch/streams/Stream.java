package com.pajelonek.clipwatcher.domain.twitch.streams;

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
public class Stream {
    @JsonProperty("id")
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_login")
    private String userLogin;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("game_name")
    private String gameName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("viewer_count")
    private int viewerCount;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("language")
    private String language;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    @JsonProperty("tag_ids")
    private List<String> tagIds;
    @JsonProperty("is_mature")
    private boolean isMature;
}