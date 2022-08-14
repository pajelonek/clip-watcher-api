package com.pajelonek.clipwatcher.domain.twitch.channels;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Channel {
    private String broadcaster_language;
    private String broadcaster_login;
    private String display_name;
    private String game_id;
    private String game_name;
    private String id;
    private boolean is_live;
    private List<String> tag_ids;
    private String thumbnail_url;
    private String title;
    private String started_at;
}