package com.pajelonek.clipwatcher.domain.twitch.streams;

import com.pajelonek.clipwatcher.domain.twitch.common.Pagination;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TopStreamsResponse {

    private List<Stream> data;

    private Pagination pagination;

    @Override
    public String toString() {
        return "CategoriesResponse{" +
                "size of data=" + data.size() +
                ", pagination=" + pagination +
                '}';
    }
}