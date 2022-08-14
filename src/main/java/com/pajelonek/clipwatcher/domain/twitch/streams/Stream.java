package com.pajelonek.clipwatcher.domain.twitch.streams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stream {
    private String id;
    private String user_id;
    private String user_login;
    private String user_name;
    private String game_id;
    private String game_name;
    private String type;
    private String title;
    private int viewer_count;
    private String started_at;
    private String language;
    private String thumbnail_url;
    private List<String> tag_ids;
    private boolean is_mature;
}