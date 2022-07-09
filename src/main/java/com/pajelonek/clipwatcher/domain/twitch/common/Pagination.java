package com.pajelonek.clipwatcher.domain.twitch.common;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Pagination {

    @NonNull
    private String cursor;
}
