package info.team23h.acc.service.event;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class EventServiceImplTest {

	@Autowired
	EventService eventService;

	@Test
	public void 이벤트_라운드_조회(){

		eventService.findEventResultWithRound(36L, 1L);
	}

}