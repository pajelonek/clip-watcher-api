package com.pajelonek.clipwatcher.util;

import com.pajelonek.clipwatcher.domain.error.DefaultException;
import com.pajelonek.clipwatcher.domain.error.Error;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsRequest;
import org.springframework.stereotype.Component;

@Component
public class ClipsRequestValidator {

    public void validateClipsRequest(ClipsRequest clipsRequest) {
        if (clipsRequest.getGameId() == null && clipsRequest.getBroadcasterId() == null && clipsRequest.getClipId() == null) {
            throw new DefaultException(Error.CLIPS_REQUIRED_MAIN_QUERY);
        }

        int requiredParams = 0;

        if (clipsRequest.getGameId() != null) {
            requiredParams++;
        }

        if (clipsRequest.getBroadcasterId() != null) {
            requiredParams++;
        }

        if (clipsRequest.getClipId() != null) {
            requiredParams++;
        }

        if (requiredParams > 1) {
            throw new DefaultException(Error.CLIPS_MORE_THAN_ONE_MAIN_QUERY_PROVIDED);
        }

        if (clipsRequest.getFirst() != null) {
            int first = Integer.parseInt(clipsRequest.getFirst());
            if (first > 100 || first < 0 ) {
                throw new DefaultException(Error.CLIPS_WRONG_FIRST_PARAMETER);
            }
        }
    }
}
