package com.pajelonek.clipwatcher.domain.twitch.clips;

import com.pajelonek.clipwatcher.domain.twitch.common.Pagination;
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
