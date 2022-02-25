package com.pajelonek.clipwatcher.util;

import com.pajelonek.clipwatcher.domain.error.DefaultException;
import com.pajelonek.clipwatcher.domain.error.Error;
import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public void validateClipsRequest(ClipsRequest clipsRequest) {
        int requiredParams = 0;
        // todo tylko jeden główny parametr może być użyty

        if (clipsRequest.getGameId() == null && clipsRequest.getBroadcasterId() == null && clipsRequest.getClipId() == null) {
            throw new DefaultException(Error.CLIPS_REQUIRED_MAIN_QUERY);
        }


        if (clipsRequest.getFirst() != null) {
            int first = Integer.parseInt(clipsRequest.getFirst());
            if (first > 100 || first < 0 ) {
                throw new DefaultException(Error.CLIPS_WRONG_FIRST_PARAMETER);
            }
        }
    }
}
