package com.pajelonek.clipwatcher;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("LOCAL")
@RunWith(SpringRunner.class)
class ClipWatcherApplicationTest {

	@Test
	void applicationStarts() {
		ClipWatcherApplication.main(new String[]{});
		assertThat(true).isTrue();
	}
}