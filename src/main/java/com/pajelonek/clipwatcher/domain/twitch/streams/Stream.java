package com.pajelonek.clipwatcher.domain.twitch.streams;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Stream {
    public String id;
    public String user_id;
    public String user_login;
    public String user_name;
    public String game_id;
    public String game_name;
    public String type;
    public String title;
    public int viewer_count;
    public String started_at;
    public String language;
    public String thumbnail_url;
    public List<String> tag_ids;
    public boolean is_mature;
}