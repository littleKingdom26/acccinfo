package info.team23h.acc.service.timeTrial;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class timeTrialServiceImplTest {

	@Autowired
	TimeTrialService timeTrialService;

	@Test
	public void 주차조회(){
		timeTrialService.findGt3WeekTimeTrial(80L);
	}

}