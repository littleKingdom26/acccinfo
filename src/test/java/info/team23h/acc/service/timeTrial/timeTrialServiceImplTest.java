package info.team23h.acc.service.timeTrial;

import info.team23h.acc.vo.front.timeTrial.TimeTrialResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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

	@Test
	public void 트랙별_조회(){

		// given
		final Long trackSeq = 54L;


		// when
		final List<TimeTrialResultVO> gt3TrackList = timeTrialService.findGt3Track(trackSeq);


		// then
		assertThat(gt3TrackList.size() > 0);


	}

}