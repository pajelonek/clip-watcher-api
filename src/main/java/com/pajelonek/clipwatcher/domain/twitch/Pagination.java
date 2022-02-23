package com.pajelonek.clipwatcher.domain.twitch;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Pagination {

    @NonNull
    private String cursor;
}
