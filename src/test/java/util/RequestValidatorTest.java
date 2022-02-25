package util;

import com.pajelonek.clipwatcher.domain.error.DefaultException;
import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.util.RequestValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("LOCAL")
@SpringBootTest(classes = {RequestValidator.class})
class RequestValidatorTest {

    @Autowired
    private RequestValidator validator;

    @Test
    void validatorShouldThrowRequiredMainQueryException() {
        ClipsRequest emptyRequest = new ClipsRequest();
        assertThrows(DefaultException.class, () -> validator.validateClipsRequest(emptyRequest));
    }

    @Test
    void validatorShouldThrowWrongFirstParameterException() {
        // given
        String wrongFirstParameter = "101";
        ClipsRequest emptyRequest = new ClipsRequest();
        emptyRequest.setClipId("dummyId");
        emptyRequest.setFirst(wrongFirstParameter);

        // when & then
        assertThrows(DefaultException.class, () -> validator.validateClipsRequest(emptyRequest));
    }
}
