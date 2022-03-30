package com.pajelonek.clipwatcher.domain.twitch.categories;

import com.pajelonek.clipwatcher.domain.twitch.common.Pagination;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CategoriesResponse {

    private List<Category> data;

    private Pagination pagination;

    @Override
    public String toString() {
        return "TopCategoriesResponse{" +
                "size of data=" + data.size() +
                ", pagination=" + pagination +
                '}';
    }
}