package com.pajelonek.clipwatcher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("LOCAL")
@SpringBootTest
class ClipWatcherApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true).isTrue();
	}

}
