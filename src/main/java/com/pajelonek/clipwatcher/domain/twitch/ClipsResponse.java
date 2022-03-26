package com.pajelonek.clipwatcher.domain.twitch;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ClipsResponse {

    private List<Clip> data;

    private Pagination pagination;

    @Override
    public String toString() {
        return "ClipsResponse{" +
                "size of data=" + data.size() +
                ", pagination=" + pagination +
                '}';
    }
}
