package com.pajelonek.clipwatcher.domain.twitch.channels;

import com.pajelonek.clipwatcher.domain.twitch.categories.Category;
import com.pajelonek.clipwatcher.domain.twitch.common.Pagination;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ChannelsResponse {

    private List<Channel> data;

    private Pagination pagination;

    @Override
    public String toString() {
        return "ChannelsResponse{" +
                "size of data=" + data.size() +
                ", pagination=" + pagination +
                '}';
    }
}