package com.pajelonek.clipwatcher.domain.twitch.categories;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Category {
    private String id;
    private String name;
    private String box_art_url;
}