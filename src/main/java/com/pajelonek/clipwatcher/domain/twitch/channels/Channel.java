package com.pajelonek.clipwatcher.domain.twitch.channels;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Channel {
    public String broadcaster_language;
    public String broadcaster_login;
    public String display_name;
    public String game_id;
    public String game_name;
    public String id;
    public boolean is_live;
    public List<String> tag_ids;
    public String thumbnail_url;
    public String title;
    public String started_at;
}