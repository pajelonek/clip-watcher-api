package com.pajelonek.clipwatcher.domain.twitch.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoriesRequest {

    private String after;

    private String before;

    private String first;
}
