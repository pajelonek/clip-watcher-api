package com.pajelonek.clipwatcher.domain.twitch.categories;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Category {
    public String id;
    public String name;
    public String box_art_url;
}